name := "Hystrix at flatMap 2013"

version := "0.1"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
    "org.specs2" %% "specs2" % "1.14" % "test",
    "com.netflix.hystrix" % "hystrix-core" % "1.0.2"
)
