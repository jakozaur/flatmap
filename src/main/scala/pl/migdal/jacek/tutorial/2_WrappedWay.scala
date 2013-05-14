package pl.migdal.jacek.tutorial

import pl.migdal.jacek.scala.hystrix.DistributedApplication

class WrappedWay extends Base {
  def wrap(func: Long => String, id: Long): Option[String] = {
    try {
      Some(func(id))
    } catch {
      case e: RuntimeException => None
    }
  }

  def render(qid: Long, app: DistributedApplication): String = {
    val auth = wrap(app.Auth.query, qid)
    val bill = wrap(app.Billing.query, qid)
    val monitor = wrap(app.ContentA.query, qid)
    val contentA = wrap(app.ContentA.query, qid)
    val contentB = wrap(app.ContentA.query, qid)
    calculate(auth, bill, monitor, contentA, contentB).get
  }
}