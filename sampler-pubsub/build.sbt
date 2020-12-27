name := "sampler-pubsub"
version := "0.0.1"

val AkkaVersion = "2.5.31"
val JacksonVersion = "2.10.5.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream-kafka" % "2.0.6",
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % JacksonVersion
)
