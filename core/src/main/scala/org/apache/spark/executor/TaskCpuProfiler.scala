/*
 * class created by Eddie
 *
 */

package org.apache.spark.executor

import java.lang.management.ManagementFactory
import java.util.concurrent.{ConcurrentHashMap, TimeUnit}

import org.apache.spark.SparkConf
import org.apache.spark.internal.Logging
import org.apache.spark.util.{ThreadUtils, Utils}


class TaskCpuProfiler(val conf: SparkConf) extends Logging {
  private val taskIdToThreadId = new ConcurrentHashMap[Long, Long]()

  // these three need to be updated every interval
  private val threadIdToPrevCpuTime = new ConcurrentHashMap[Long, Long]()
  private val threadIdToPrevSystemTime = new ConcurrentHashMap[Long, Long]()
  private val taskIdToCpuUsage = new ConcurrentHashMap[Long, Double]()

  // used for finished but unreported tasks
  private val unreportedTaskIdToCpuUsage = new ConcurrentHashMap[Long, Double]()

  private val threadMXBean = ManagementFactory.getThreadMXBean
  private val osMXBean = ManagementFactory.getOperatingSystemMXBean
  private val cores = osMXBean.getAvailableProcessors

  private val profileInterval = conf.getTimeAsMs("spark.tracing.profilingInterval", "3s")

  private val cpuProfileThread =
    ThreadUtils.newDaemonSingleThreadScheduledExecutor("cpu-profile-executor")

  // called only after the task is started
  @volatile def registerTask(taskId: Long, threadId: Long): Unit = {
    logDebug("registered task: " + taskId + " thread: " + threadId)
    taskIdToThreadId.put(taskId, threadId)
    updatePrevTimesForThread(threadId)
  }

  @volatile def unregisterTask(taskId: Long): Unit = {
    val threadId = taskIdToThreadId.get(taskId)

    if (threadIdToPrevCpuTime.containsKey(threadId)) {
      unreportedTaskIdToCpuUsage.put(taskId, profileOneTaskCpuUsage(taskId))
      logDebug("unregistered task: " + taskId +
        " thread: " + threadId +
        " cpu usage: " + unreportedTaskIdToCpuUsage.get(taskId))
    }
    // remove the finished task from these map
    taskIdToThreadId.remove(taskId)
    removePrevTimesForThread(threadId)
    taskIdToCpuUsage.remove(taskId)
  }

  @volatile private def updatePrevTimesForThread (threadId: Long): Unit = {
    threadIdToPrevCpuTime.put(threadId, threadMXBean.getThreadCpuTime(threadId))
    threadIdToPrevSystemTime.put(threadId, System.currentTimeMillis())
  }

  @volatile private def removePrevTimesForThread (threadId: Long): Unit = {
    threadIdToPrevCpuTime.remove(threadId)
    threadIdToPrevSystemTime.remove(threadId)
  }

  @volatile def getTaskCpuUsage(taskId: Long): Double = {
    if (taskIdToCpuUsage.containsKey(taskId)) {
      val cpuUsage = taskIdToCpuUsage.get(taskId)
      logDebug("reporting running task: " + taskId + " cpu usage: " + cpuUsage)
      cpuUsage
    } else if (unreportedTaskIdToCpuUsage.containsKey(taskId)) {
      val cpuUsage = unreportedTaskIdToCpuUsage.remove(taskId)
      logDebug("reporting unreported task: " + taskId + " cpu usage: " + cpuUsage)
      cpuUsage
    } else {
      -1D
    }
  }

  private def profileAllTasksCpuUsage(): Unit = {

    // calculate the cpu usage for each thread
    val entryIterator = taskIdToThreadId.entrySet().iterator()
    logDebug("number of keys: " + taskIdToThreadId.size())
    while (entryIterator.hasNext) {
      val key = entryIterator.next().getKey
      profileOneTaskCpuUsage(key)
    }
  }

  @volatile private def profileOneTaskCpuUsage (taskId: Long): Double = {
    if (taskIdToThreadId.containsKey(taskId)) {
      logDebug("profiling cpu usage for task: " + taskId)
      val threadId = taskIdToThreadId.get(taskId)
      val curTime: Long = System.currentTimeMillis()
      val elapsedTime: Double = (curTime - threadIdToPrevSystemTime.get(threadId))

      val curCpuTime = threadMXBean.getThreadCpuTime(threadId)
      val elapsedCpuTime = (curCpuTime - threadIdToPrevCpuTime.get(threadId)) / 1000000D
      var cpuUsage: Double =
        Math.min(0.99D,
          (elapsedCpuTime / (elapsedTime * cores)))
      if (cpuUsage.isNaN) {
        cpuUsage = 0.0D
      }
      taskIdToCpuUsage.put(taskId, cpuUsage)

      // update the previous time for each thread
      updatePrevTimesForThread(threadId)
      logDebug("cpu usage for task: " + taskId + " is " + cpuUsage)
      cpuUsage
    } else -1D
  }

  private[executor] def start(): Unit = {
    val intervalMs = profileInterval

    // Wait a random interval so the heartbeats don't end up in sync
    val initialDelay = intervalMs + (math.random * intervalMs).asInstanceOf[Int]

    val profileTask = new Runnable() {
      override def run(): Unit = Utils.logUncaughtExceptions(profileAllTasksCpuUsage())
    }
    cpuProfileThread.scheduleAtFixedRate(
      profileTask, initialDelay, profileInterval, TimeUnit.MILLISECONDS)
  }

  // report unreported tasks
  private[executor] def stop(): Unit = {
    cpuProfileThread.shutdown()
    cpuProfileThread.awaitTermination(10, TimeUnit.SECONDS)
  }
}
