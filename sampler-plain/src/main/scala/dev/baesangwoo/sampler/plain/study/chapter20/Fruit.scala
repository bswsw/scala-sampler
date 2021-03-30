package dev.baesangwoo.sampler.plain.study.chapter20

abstract class Fruit {
  val v: String
  def m: String
}

abstract class Apple extends Fruit {
  override val v: String = "apple"
  override val m: String = "ddd"
}
