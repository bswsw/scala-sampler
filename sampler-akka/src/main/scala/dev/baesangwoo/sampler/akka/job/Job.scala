package dev.baesangwoo.sampler.akka.job

import akka.actor.{Actor, ActorRef, Props}
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag

object Job {
  case class ChunkInfo(id: Long, size: Int)
  case class Start()
  case class Continue()
  case class Complete()
}

trait Job extends Actor {

  import Job._

  implicit val ec: ExecutionContext
  implicit val timeout: Timeout

  val writer: ActorRef
  val processor: ActorRef
  val reader: ActorRef

  override def receive: Receive = { case Start() =>
    reader ! ChunkInfo(-1, 1000)
  }

  protected def props[T <: Actor: ClassTag](creator: => T): ActorRef = {
    context.actorOf(Props(creator))
  }
}

class MyJob(override implicit val ec: ExecutionContext, override implicit val timeout: Timeout = 10.seconds)
    extends Job {

  override val writer: ActorRef = props(new MyWriterActor)
  override val processor: ActorRef = props(new MyProcessorActor(writer))
  override val reader: ActorRef = props(new MyReaderActor(processor))
}

class MyReaderActor(override val processor: ActorRef) extends ReaderActor[String] {
  override def read(chunkInfo: Job.ChunkInfo): Future[Seq[String]] = {
    Future.successful(Seq("a", "b", "c"))
  }
}

class MyProcessorActor(override val writer: ActorRef) extends ProcessorActor[String, String] {
  override def process(inputs: Seq[String]): Future[Seq[String]] = {
    Future.successful {
      inputs.map(input => input + input.toUpperCase)
    }
  }
}

class MyWriterActor extends WriterActor[String] {
  override def write(outputs: Seq[String]): Unit = {
    Future.successful {
      outputs.map(println)
    }
  }
}
