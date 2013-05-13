package pl.migdal.jacek.tutorial

import pl.migdal.jacek.scala.hystrix.DistributedApplication
import javax.swing.text.html.Option


class NaiveWay extends Base {
  def render(qid: Long, app: DistributedApplication): String = {
    val auth = app.Auth.query(qid)
    val bill = app.Billing.query(qid)
    val monitor = app.ContentA.query(qid)
    val contentA = app.ContentA.query(qid)
    val contentB = app.ContentA.query(qid)
    calculate(auth, bill, monitor, contentA, contentB).get
  }
}

