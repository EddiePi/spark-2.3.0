
package org.apache.spark.executor

import java.util.concurrent.{ConcurrentHashMap, TimeUnit}

import org.apache.spark.SparkEnv
import org.apache.spark.internal.Logging
import org.apache.spark.memory.TaskMemoryManager
import org.apache.spark.scheduler.Task
import org.apache.spark.tracing.TaskInfo
import org.apache.spark.util.{ThreadUtils, Utils}

import scala.collection.mutable


private[executor] class TaskProfileManager (val env: SparkEnv) extends Logging {
  val conf = env.conf
  val tracingManager = env.tracingManager

  val runningTasks = new ConcurrentHashMap[Long, TaskInfo]
  val unreportedTasks = new ConcurrentHashMap[Long, TaskInfo]

  val taskCpuProfiler: TaskCpuProfiler = new TaskCpuProfiler(conf)
  val taskMemoryProfiler: TaskMemoryProfiler = new TaskMemoryProfiler(env)

  taskCpuProfiler.start()
  taskMemoryProfiler.start()

  // Edit by Eddie
  // Tracing heartbeat
  private val tracingHeartbeater = ThreadUtils.newDaemonSingleThreadScheduledExecutor("tracing-heartbeater")

  @volatile def registerTask(taskId: Long,
                             task: Task[Any],
                             threadId: Long,
                             taskMemoryManager: TaskMemoryManager,
                             containerId: String
                            ): Unit = {
    if (!runningTasks.containsKey(taskId)) {
      runningTasks.put(taskId, new TaskInfo(
        taskId,
        task.stageId,
        task.stageAttemptId,
        task.jobId.getOrElse(-1),
        task.appId.getOrElse("anonymous-app"),
        System.currentTimeMillis(),
        -1L,
        -1.0D,
        -1L,
        -1L,
        "RUNNING",
        containerId
      ))
      taskCpuProfiler.registerTask(taskId, threadId)
      taskMemoryProfiler.registerTask(taskId, taskMemoryManager)
      logDebug("task: %d is registered".format(taskId))
    }
  }

  /*
    update finished task information here
   */
  @volatile def unregisterTask(taskId: Long, status: String): Unit = {
    if (runningTasks.containsKey(taskId)) {
      val taskInfo = runningTasks.get(taskId)
      // update taskInfo when task finished.
      taskInfo.finishTime = System.currentTimeMillis()
      taskInfo.status = status

      runningTasks.remove(taskId)

      // First we unregister the task from the profiler.
      // the profiler will also mark the task as finished.
      taskCpuProfiler.unregisterTask(taskId)
      taskMemoryProfiler.unregisterTask(taskId)

      // we get the unreported task info from prepareTaskTracingInfo method.
      // do not get the unreported task info twice, otherwise causes cpu usage to be -1
      // taskInfo.cpuUsage = taskCpuProfiler.getTaskCpuUsage(taskId)

      if (!unreportedTasks.containsKey(taskId)) {
        unreportedTasks.put(taskId, taskInfo)
      }
      logDebug("task: %d is unregistered".format(taskId))
    }
  }

  // Edit by Eddie
  /**
    * collect and prepare the task tracing information
    */
  @volatile private def prepareTaskTracingInfo(): mutable.Set[TaskInfo] = {
    val taskSet: mutable.Set[TaskInfo] = new mutable.HashSet[TaskInfo]()
    // prepare running tasks
    val runningIterator = runningTasks.entrySet().iterator()
    while (runningIterator.hasNext) {
      val key = runningIterator.next().getKey
      val runningTaskInfo = runningTasks.get(key)
      // get the cpu usage
      runningTaskInfo.cpuUsage = taskCpuProfiler.getTaskCpuUsage(key)
      // get the memory usage
      // we have to get storage memory first
      runningTaskInfo.storeMemory = taskMemoryProfiler.getTaskStoreMemoryUsage(key)
      runningTaskInfo.execMemory = taskMemoryProfiler.getTaskExecMemoryUsage(key)
      taskSet.add(runningTaskInfo)
    }
    // prepare unreported tasks
    val unreportedIterator = unreportedTasks.entrySet().iterator()
    while (unreportedIterator.hasNext) {
      val key = unreportedIterator.next().getKey
      // we remove the unreported tasks once they get a change to report
      val unreportedTaskInfo = unreportedTasks.remove(key)
      // get the cpu usage
      unreportedTaskInfo.cpuUsage = taskCpuProfiler.getTaskCpuUsage(key)
      unreportedTaskInfo.storeMemory = taskMemoryProfiler.getTaskStoreMemoryUsage(key)
      unreportedTaskInfo.execMemory = taskMemoryProfiler.getTaskExecMemoryUsage(key)
      taskSet.add(unreportedTaskInfo)
    }
    taskSet
  }

  // Edit by Eddie
  private def reportTracingInfo(): Unit = {
    val taskSet = prepareTaskTracingInfo()
    for (taskInfo <- taskSet) {
      logDebug("reporting tracing heartbeat. Size of taskSet is: " + taskSet.size)
      tracingManager.createOrUpdateTaskInfo(taskInfo)
    }
  }

  // Edit by Eddie
  private[executor] def startTracingHeartbeater(): Unit = {
    val intervalMs = conf.getTimeAsMs("spark.tracing.heartbeatInterval", "2s")

    // Wait a random interval so the heartbeats don't end up in sync
    val initialDelay = intervalMs + (math.random * intervalMs).asInstanceOf[Int]

    val heartbeatTask = new Runnable() {
      override def run(): Unit = Utils.logUncaughtExceptions(reportTracingInfo())
    }
    tracingHeartbeater.scheduleAtFixedRate(
      heartbeatTask, initialDelay, intervalMs, TimeUnit.MILLISECONDS)
  }

  def stop(): Unit = {
    taskCpuProfiler.stop()
    taskMemoryProfiler.stop()
    tracingHeartbeater.shutdown()
    tracingHeartbeater.awaitTermination(10, TimeUnit.SECONDS)
    reportTracingInfo()
  }
}
