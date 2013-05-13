package pl.migdal.jacek.scala.hystrix

class DistributedApplication {
  val Auth = new WebService(42L, 200, 100, 0.01, 0.01, 0.01)
  val Billing = new WebService(43L, 200, 100, 0.01, 0.01, 0.01)
  val Monitoring = new WebService(44L, 200, 100, 0.01, 0.01, 0.01)
  val ContentA = new WebService(45L, 200, 100, 0.01, 0.01, 0.01)
  val ContentB = new WebService(46L, 200, 100, 0.01, 0.01, 0.01)

}
