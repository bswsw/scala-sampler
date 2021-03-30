// ref : https://github.com/softwaremill/bootzooka/blob/master/build.sbt#L18

organization := "dev.baesangwoo"
scalaVersion := "2.13.4"

lazy val commonDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.typelevel" %% "cats-core" % "2.1.0"
)

lazy val testCommonDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.2" % Test
)

lazy val commonScalacOptions = Seq(
//  "-Xfatal-warnings"
)

lazy val commonSettings = Seq(
  scalaVersion := "2.13.4",
//  scalacOptions ++= commonScalacOptions,
  scalacOptions += "-deprecation",
  libraryDependencies ++= commonDependencies ++ testCommonDependencies
)

lazy val autoconfigure = (project in file("sampler-autoconfigure"))
  .settings(
    name := "sampler-autoconfigure",
    commonSettings
  )

lazy val plain = (project in file("sampler-plain"))
  .settings(
    name := "sampler-plain",
    commonSettings
  )
  .dependsOn(autoconfigure)

lazy val api = (project in file("sampler-api"))
  .enablePlugins(PlayScala)
  .settings(
    name := "sampler-api",
    commonSettings
  )

lazy val domain = (project in file("sampler-domain"))
  .settings(
    name := "sampler-domain",
    commonSettings
  )

lazy val pubsub = (project in file("sampler-pubsub"))
  .settings(
    name := "sampler-pubsub",
    commonSettings
  )

lazy val akka = (project in file("sampler-akka"))
  .settings(
    name := "sampler-akka",
    commonSettings
  )