package pl.migdal.jacek.tutorial

import pl.migdal.jacek.scala.hystrix.{PerformanceTestsHelper, DistributedApplication}
import com.netflix.hystrix._
import java.util.concurrent.Future

object HystrixWay {
  val HystrixThreads = 5 * PerformanceTestsHelper.ThreadCount
  val CommandKey = HystrixCommandGroupKey.Factory.asKey("HystrixWay")
  val ThreadPool = HystrixThreadPoolProperties.Setter().
    withCoreSize(HystrixThreads).
    withMaxQueueSize(HystrixThreads)
  val options = HystrixCommand.Setter.withGroupKey(CommandKey).andThreadPoolPropertiesDefaults(ThreadPool)
}


class HystrixWay extends Base {
  def wrap(func: Long => String, id: Long): Future[Option[String]] = {
    val cmd = new HystrixCommand[Option[String]](HystrixWay.options) {
      def run: Option[String] = {
        Some(func(id))
      }
    }
    cmd.queue()
  }

  def render(qid: Long, app: DistributedApplication): String = {
    val auth = wrap(app.Auth.query, qid)
    val bill = wrap(app.Billing.query, qid)
    val monitor = wrap(app.ContentA.query, qid)
    val contentA = wrap(app.ContentA.query, qid)
    val contentB = wrap(app.ContentA.query, qid)
    calculate(auth.get, bill.get, monitor.get, contentA.get, contentB.get).get
  }
}