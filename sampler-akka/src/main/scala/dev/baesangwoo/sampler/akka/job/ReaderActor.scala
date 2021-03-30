package dev.baesangwoo.sampler.akka.job

import akka.actor.{Actor, ActorRef}
import akka.pattern.pipe
import dev.baesangwoo.sampler.akka.job.Job.ChunkInfo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

abstract class ReaderActor[INPUT] extends Actor {

  val processor: ActorRef

  override def receive: Receive = {
    case chunkInfo: ChunkInfo =>
      read(chunkInfo)
        .map { list =>
          processor ! list
        }
        .pipeTo(self)
  }

  def read(chunkInfo: ChunkInfo): Future[Seq[INPUT]]
}
