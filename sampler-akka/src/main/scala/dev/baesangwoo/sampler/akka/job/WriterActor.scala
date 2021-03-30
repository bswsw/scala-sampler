package dev.baesangwoo.sampler.akka.job

import akka.actor.Actor

trait WriterActor[OUTPUT] extends Actor {
  override def receive: Receive = {
    case outputs: Seq[OUTPUT] if outputs.nonEmpty =>
    case _: Seq[OUTPUT] =>
    case _ =>
  }

  def write(outputs: Seq[OUTPUT]): Unit
}
