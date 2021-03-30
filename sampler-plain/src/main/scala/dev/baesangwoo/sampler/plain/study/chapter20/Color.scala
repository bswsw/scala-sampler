package dev.baesangwoo.sampler.plain.study.chapter20

object Color extends Enumeration {
  val Red = Value
  val Green = Value
  val Blue = Value
}

object Direction extends Enumeration {
  val North = Value("1")
  val East = Value("East")
  val South = Value("South")
  val West = Value("West")
}

object Enums extends App {
  for (d <- Direction.values) print(d + " "); println()
  for (c <- Color.values) print(c + " "); println()
}