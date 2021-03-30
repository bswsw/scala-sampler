package dev.baesangwoo.sampler.akka.job

import akka.actor.{Actor, ActorRef}

import scala.concurrent.Future

trait ProcessorActor[INPUT, OUTPUT] extends Actor {

  val writer: ActorRef

  override def receive: Receive = {
    case inputs: Seq[INPUT] if inputs.nonEmpty => writer ! process(inputs)
    case _: Seq[INPUT]                         => sender() ! Seq.empty[OUTPUT]
    case _                                     => sender() ! Seq.empty[OUTPUT]
  }

  def process(inputs: Seq[INPUT]): Future[Seq[OUTPUT]]
}
