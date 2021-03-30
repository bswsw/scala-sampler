//package dev.baesangwoo.sampler.akka.sample
//
//import akka.actor.{Actor, ActorRef}
//import akka.pattern.ask
//import akka.util.Timeout
//import cats.data.NonEmptyList
//
//import scala.collection.mutable
//import scala.concurrent.{ExecutionContext, Future}
//
//trait JobActor extends Actor {
//  type INPUT
//  type OUTPUT
//
//  val reader: ReaderActor[INPUT] with ActorRef
//  val processor: ProcessActor[INPUT] with ActorRef
//
//  implicit val ec: ExecutionContext = context.dispatcher
//  implicit val timeout: Timeout
//
//  final def run(): Unit = {
//    (reader ? ReaderActor.Read())
//      .mapTo[ReaderActor.Result[INPUT]]
//      .map(message => NonEmptyList.fromList(message.data.toList))
//      .map {
//        case None =>
//        case Some(list) =>
//      }
//  }
//}
//
//object ReaderActor {
//  case class Read()
//  case class Result[INPUT](data: Seq[INPUT])
//}
//
//trait ReaderActor[INPUT] extends Actor {
//
//  final override def receive: Receive = {
//    ???
//  }
//
//  def read(chunkFilter: ChunkFilter): Future[Seq[INPUT]]
//}
//
//trait ProcessActor[INPUT] extends Actor {
//  final override def receive: Receive = ???
//}
//
//trait TestActor[INPUT] {
//  def test(chunkFilter: ChunkFilter): Future[Seq[INPUT]]
//}
//
//class MyReader extends ReaderActor[DeadLetter] {
//  override def read(chunkFilter: ChunkFilter): Future[Seq[DeadLetter]] = ???
//}
//
//object MyApp extends App {
//  val da = new TestActor[DeadLetter] {
//    override def test(chunkFilter: ChunkFilter): Future[Seq[DeadLetter]] = {
//      ???
//    }
//  }
//}
