package dev.baesangwoo.sampler.pubsub.stream

import akka.actor.ActorSystem
import akka.stream.scaladsl.Source

import scala.concurrent.ExecutionContext

object StreamSimpleApp extends App {

  implicit val system: ActorSystem = ActorSystem("StreamApp")
  implicit val ec: ExecutionContext = system.dispatcher

  val source = Source(1 to 100)
  val done = source.runForeach(println)

  done.onComplete(_ => system.terminate())

}
