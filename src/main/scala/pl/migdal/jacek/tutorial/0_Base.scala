package pl.migdal.jacek.tutorial

import pl.migdal.jacek.scala.hystrix.DistributedApplication

trait Base {
  implicit def strToOpt(str: String): Option[String] = Some(str)
  def render(qid: Long, app: DistributedApplication): String
  def calculate(auth: Option[String], bill: Option[String], monitor: Option[String],
                contentA: Option[String], contentB: Option[String]): Option[String] = {
    if (auth.isDefined && contentA.isDefined && contentB.isDefined)
      Some("Works!")
    else
      None
  }
}

