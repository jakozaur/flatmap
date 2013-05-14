package pl.migdal.jacek.tutorial

import java.util.concurrent.Future
import com.netflix.hystrix._
import pl.migdal.jacek.scala.hystrix.{PerformanceTestsHelper, DistributedApplication}

class RetryingCmd(func: Long => String, id: Long) {
  private var (cmd, res) = runCmd

  private def runCmd = {
    val cmd = new HystrixCommand[Option[String]](HystrixWay.options) {
      def run: Option[String] = {
        Some(func(id))
      }

      override def getFallback: Option[String] = None
    }
    (cmd, cmd.queue())
  }

  def retryIfFail {
    res.get match {
      case None => {
        val tmp = runCmd
        cmd = tmp._1
        res = tmp._2
      }
      case _ => {}
    }
  }

  def get: Option[String] = res.get

}
class HystrixWay2 extends Base {
  def wrap(func: Long => String, id: Long): RetryingCmd = {
    new RetryingCmd(func, id)
  }

  def render(qid: Long, app: DistributedApplication): String = {
    val auth = wrap(app.Auth.query, qid)
    val bill = wrap(app.Billing.query, qid)
    val monitor = wrap(app.ContentA.query, qid)
    val contentA = wrap(app.ContentA.query, qid)
    val contentB = wrap(app.ContentA.query, qid)
    auth.retryIfFail
    bill.retryIfFail
    monitor.retryIfFail
    contentA.retryIfFail
    contentB.retryIfFail
    calculate(auth.get, bill.get, monitor.get, contentA.get, contentB.get).get
  }
}