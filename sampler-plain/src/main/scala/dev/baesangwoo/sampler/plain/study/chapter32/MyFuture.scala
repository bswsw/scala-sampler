package dev.baesangwoo.sampler.plain.study.chapter32

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object MyFuture extends App {

  implicit class RichFuture[T](future: Future[T]) {
    def await: T = Await.result(future, Duration.Inf)
  }

  private def failure = Future.failed {
    println("hello failure")
    new RuntimeException("error")
  }

  private def success = Future.successful {
    println("hello success")
  }

  private def success2 = Future.successful {
    println("hello success2")
  }

  private def success3 = Future.successful {
    println("hello success3")
  }

  success
    .recoverWith { case e =>
      success2
    }
    .await

  println("==================================")

  success
    .fallbackTo {
      success3
    }
    .await
}
