package dev.baesangwoo.sampler.plain

import cats.Show
import cats.implicits.{catsStdShowForInt, toShow}
import dev.baesangwoo.sampler.autoconfigure.UserProperties

object PlainApp extends App {

  println("PlainApp Start!")

  val userProperties = new UserProperties
  println(userProperties)

  val showInt = Show.apply[Int]

  showInt.show(123)
  123.show
}
