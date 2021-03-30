package dev.baesangwoo.sampler.pubsub

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import dev.baesangwoo.sampler.pubsub.consumer.EventConsumer
import dev.baesangwoo.sampler.pubsub.producer.EventProducer
import dev.baesangwoo.sampler.pubsub.stream.SampleStream

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

object PubsubApp extends App {

  implicit val system: ActorSystem = ActorSystem("MyPubsub", ConfigFactory.defaultApplication())
  implicit val ec: ExecutionContext = system.dispatcher

  println("PubsubApp Start!")

//  val producer = new EventProducer
//  producer.sendMessage()

//  val consumer = new EventConsumer
//  consumer.consume()

  val sample = new SampleStream
  val result = sample.stream2()
  result.onComplete { data =>
    println(data.get)

    system.terminate()
  }
}
