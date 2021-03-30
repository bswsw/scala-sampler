package dev.baesangwoo.sampler.plain.study.chapter23

object MyFor extends App {

  val list = List(1, 2, 3)

  println(list.withFilter(_ > 2))
  println(list.filter(_ > 1))
}
