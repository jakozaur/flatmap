package pl.migdal.jacek.scala.hystrix

import java.util.Random
import scala.collection.mutable.{HashSet, SynchronizedSet}

class WebService(randSeed: Long,
                 meanMs: Long,
                 stdDevMs: Long,
                 chanceForNeverFinish: Double,
                 chanceForException: Double,
                 chanceForBroken: Double) {
  private val chanceForSpecialEvent = chanceForNeverFinish + chanceForException + chanceForBroken
  private val gen = new Random(randSeed)
  private val brokenIds = new HashSet[Long] with SynchronizedSet[Long]


  def query(qid: Long): String = {
    if (brokenIds.contains(qid)) {
      throw new RuntimeException("Something has broken :-( (always)")
    }

    val (eventType, latency) = synchronized {
      (gen.nextDouble(), gen.nextGaussian())
    }

    if (eventType < chanceForSpecialEvent) {
      if (eventType < chanceForNeverFinish) {
        while (true) {}
        ""
      } else if (eventType < chanceForNeverFinish + chanceForException) {
        throw new RuntimeException("Something has broken :-( (once)")
      } else {
        brokenIds.add(qid)
        throw new RuntimeException("Something has broken :-( (always, first)")
      }
    } else {
      val waitForMs = Math.max(0.0, latency * stdDevMs + meanMs).toLong
      Thread.sleep(waitForMs)
      "The system works!"
    }
  }

}
