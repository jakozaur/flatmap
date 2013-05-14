package pl.migdal.jacek.scala.hystrix

class DistributedApplication {
  val BreakTimeout = 0.012
  val BreakException = 0.004
  val Auth = new WebService(42L, 100, 100, BreakTimeout, BreakException, BreakException)
  val Billing = new WebService(43L, 200, 100, BreakTimeout, BreakException, BreakException)
  val Monitoring = new WebService(44L, 100, 100, BreakTimeout, BreakException, BreakException)
  val ContentA = new WebService(45L, 200, 100, BreakTimeout, BreakException, BreakException)
  val ContentB = new WebService(46L, 200, 100, BreakTimeout, BreakException, BreakException)

}
