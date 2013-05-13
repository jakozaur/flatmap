package pl.migdal.jacek.scala.hystrix

class DistributedApplication {
  val BreakPer = 0.005
  val Auth = new WebService(42L, 100, 100, BreakPer, BreakPer, BreakPer)
  val Billing = new WebService(43L, 200, 100, BreakPer, BreakPer, BreakPer)
  val Monitoring = new WebService(44L, 100, 100, BreakPer, BreakPer, BreakPer)
  val ContentA = new WebService(45L, 200, 100, BreakPer, BreakPer, BreakPer)
  val ContentB = new WebService(46L, 200, 100, BreakPer, BreakPer, BreakPer)

}
