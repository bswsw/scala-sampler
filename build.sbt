// ref : https://github.com/softwaremill/bootzooka/blob/master/build.sbt#L18

organization := "dev.baesangwoo"
scalaVersion := "2.13.4"

lazy val commonDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)

lazy val testCommonDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.2" % Test
)

lazy val commonSettings = Seq(
  scalaVersion := "2.13.4",
  //  scalacOptions += "-deprecation",
  libraryDependencies ++= commonDependencies ++ testCommonDependencies
)

lazy val plain = (project in file("sampler-plain"))
  .settings(
    commonSettings
  )

lazy val api = (project in file("sampler-api"))
//  .enablePlugins(PlayScala)
  .settings(
    name := "api",
    commonSettings
  )

lazy val domain = (project in file("sampler-domain"))
  .settings(
    name := "domain",
    commonSettings
  )

lazy val pubsub = (project in file("sampler-pubsub"))
  .settings(
    name := "pubsub",
    commonSettings
  )
