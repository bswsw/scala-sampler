package dev.baesangwoo.sampler.pubsub.stream

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.kafka.ConsumerMessage.CommittableOffset
import akka.kafka.ProducerMessage
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}

import scala.concurrent.{ExecutionContext, Future}

class SampleStream(implicit system: ActorSystem, ec: ExecutionContext) {

  def stream(): Future[Done] = {
    Source(1 to 100)
      .mapAsyncUnordered(10) { i =>
        Thread.sleep(500)
        Future.successful(println(s"asdsad : $i"))
      }
      .runWith(Sink.ignore)
  }

  def stream4 = ""

  def stream2() = {
//    val source = Source(1 to 6)
//    val flow = Flow[Int].map(_.toString)
//    val sink = Sink.foreach(println)
//
//    val graph = source.via(flow).to(sink)

    val source = Source(1 to 100)
    val sink = Sink.fold[String, Int]("")(_ + _)

    val graph = source.toMat(sink)(Keep.right)
    graph.run()
//    val source = FileIO.fromPath(path)
//    val sink = FileIO.toPath()

    val flow: Flow[ProducerMessage.Envelope[String, Array[Byte], CommittableOffset], CommittableOffset, NotUsed] = Flow.futureFlow {

    }
  }

  def stream3() = {}
}
