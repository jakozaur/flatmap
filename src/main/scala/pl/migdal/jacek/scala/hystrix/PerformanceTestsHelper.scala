package pl.migdal.jacek.scala.hystrix

import pl.migdal.jacek.tutorial.{Base, NaiveWay}
import java.util.concurrent.atomic.{AtomicIntegerArray, AtomicInteger}
import java.util.Date

object PerformanceTestsHelper {
  val ThreadCount = 200
  val TimeOutMs = 3 * 1000
}

class PerformanceTestsHelper {
  import PerformanceTestsHelper._

  def runTest(name: String, app: Base) {
    val env = new DistributedApplication
    val ran = new AtomicInteger(0)
    val ok = new AtomicInteger(0)
    val fail = new AtomicInteger(0)
    val totalTime = new AtomicIntegerArray(ThreadCount)


    val threads = (1 to ThreadCount).map(id => new Thread {
      override def run {
        ran.incrementAndGet()
        try {
          val begin = new Date().getTime
          app.render(id, env)
          val end = new Date().getTime
          totalTime.set(id - 1, (end - begin).toInt)
          ok.incrementAndGet()
        } catch {
          case e: java.lang.OutOfMemoryError => {
            println("Out of memory error")
          }
          case e: Throwable => {
            fail.incrementAndGet()
          }
        }
      }
    })

    threads.foreach(_.start)

    Thread.sleep(TimeOutMs)
    val ranCount = ran.get()
    val okCount = ok.get()
    val failCount = fail.get()
    val timedOut = ThreadCount - okCount - failCount
    val timeArray = (0 to (ThreadCount-1)).map(i => totalTime.get(i)).sorted.drop(ranCount - okCount)
    val per90Index = Math.floor(timeArray.length * 0.90).toInt
    val per90 = if (timeArray.length > 0) timeArray(per90Index) else 0

    threads.foreach(_.stop)

    println(s"Tests done $name")
    println(f"RUN: $ranCount%5d")
    println(f"OK : $okCount%5d")
    println(f"ERR: $failCount%5d")
    println(f"TLE: $timedOut%5d")
    println(f"90%: $per90%5d ms")
  }

}
