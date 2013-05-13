package pl.migdal.jacek.scala.hystrix

import org.specs2.mutable._
import scala.concurrent._
import ExecutionContext.Implicits.global
import java.util.concurrent.atomic.AtomicBoolean

class WebServiceTest extends Specification {
  "WebService" should {
    "return an exception for sometimes broken" in {
      val service = new WebService(0L, 0L, 0L, 0.0, 1.0, 0.0)
      service.query(0) must throwA[RuntimeException]
    }

    "return an exception for always broken" in {
      val service = new WebService(0L, 0L, 0L, 0.0, 0.0, 1.0)
      service.query(0) must throwA[RuntimeException]
    }

    "never finish for that argument" in {
      val service = new WebService(0L, 0L, 0L, 1.0, 0.0, 0.0)
      val fail = new AtomicBoolean(false)
      future {
        try {
          service.query(0)
        } catch {
          case e: Throwable => {}
        }
        fail.set(true)
      }
      Thread.sleep(100)
      fail.get() must beEqualTo(false)
    }
  }

}
