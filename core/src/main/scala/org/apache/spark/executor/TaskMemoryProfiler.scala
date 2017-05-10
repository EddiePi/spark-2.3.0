
package org.apache.spark.executor

import java.util.concurrent.{ConcurrentHashMap, TimeUnit}

import org.apache.spark.SparkEnv
import org.apache.spark.internal.Logging
import org.apache.spark.memory.TaskMemoryManager
import org.apache.spark.util.{ThreadUtils, Utils}

/*
* only periodically profile execution memory
* storage memory is set when job about to write
*
*/
class TaskMemoryProfiler (env: SparkEnv) extends Logging {
  val conf = env.conf
  val memoryManager = env.memoryManager

  val taskIdToManager = new ConcurrentHashMap[Long, TaskMemoryManager]
  val taskIdToExecMemory = new ConcurrentHashMap[Long, Long]
  val taskIdToStoreMemory = new ConcurrentHashMap[Long, Long]

  val unreportedTaskIdToExecMemory = new ConcurrentHashMap[Long, Long]
  // val unreportedTaskIdToStoreMemory = new ConcurrentHashMap[Long, Long]

  private val profileInterval = conf.getTimeAsMs("spark.tracing.profilingInterval", "3s")

  private val memoryProfileThread =
    ThreadUtils.newDaemonSingleThreadScheduledExecutor("cpu-profile-executor")

  @volatile def registerTask(taskId: Long, taskMemoryManager: TaskMemoryManager): Unit = {
    if (!taskIdToManager.containsKey(taskId)) {
      taskIdToManager.put(taskId, taskMemoryManager)
    }
  }

  @volatile def unregisterTask(taskId: Long): Unit = {
    if (taskIdToManager.containsKey(taskId)) {
      val execMem = profileOneTaskExecMemory(taskId)
      unreportedTaskIdToExecMemory.put(taskId, execMem)
      // TaskMemoryManager can be released this time since it's part of a Task
      taskIdToManager.remove(taskId)
    }
  }


  // this following methods only read the memory record from the Map.
  // do NOT profile again in these methods.
  def getTaskExecMemoryUsage(taskId: Long): Long = {
    val execMem = {
      if (taskIdToExecMemory.containsKey(taskId)) {
        taskIdToExecMemory.get(taskId)
      } else if (unreportedTaskIdToExecMemory.containsKey(taskId)) {
        // if the task is finished but unreported, we delete its storage memory record
        taskIdToStoreMemory.remove(taskId)
        unreportedTaskIdToExecMemory.get(taskId)
      } else {
        -1L
      }
    }
    execMem
  }

  // in each heartbeat, this method must be called after the previous method.
  def getTaskStoreMemoryUsage(taskId: Long): Long = {
    val storeMem = {
      if (taskIdToStoreMemory.containsKey(taskId)) {
        taskIdToStoreMemory.get(taskId)
      } else {
        -1L
      }
    }
    storeMem
  }

  private def profileAllTasksExecMemoryUsage(): Unit = {
    val entryIterator = taskIdToManager.entrySet().iterator()
    while (entryIterator.hasNext) {
      val key = entryIterator.next().getKey
      profileOneTaskExecMemory(key)
    }
  }

  private def profileOneTaskExecMemory(taskId: Long): Long = {
    val execMem = taskIdToManager.get(taskId).getMemoryConsumptionForThisTask
    taskIdToExecMemory.put(taskId, execMem)
    execMem
  }

  // called in ShuffleMapTask and ResultTask
  @volatile def setTaskStoreMemory(taskId: Long, size: Long): Unit = {
    taskIdToStoreMemory.put(taskId, size)
  }

  private[executor] def start(): Unit = {
    val intervalMs = profileInterval

    // Wait a random interval so the heartbeats don't end up in sync
    val initialDelay = intervalMs + (math.random * intervalMs).asInstanceOf[Int]

    val profileTask = new Runnable() {
      override def run(): Unit = Utils.logUncaughtExceptions(profileAllTasksExecMemoryUsage())
    }
    memoryProfileThread.scheduleAtFixedRate(
      profileTask, initialDelay, profileInterval, TimeUnit.MILLISECONDS)
  }

  @volatile def stop(): Unit = {
    memoryProfileThread.shutdown()
    memoryProfileThread.awaitTermination(10, TimeUnit.SECONDS)
  }
}
