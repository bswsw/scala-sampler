package dev.baesangwoo.sampler.plain.study.chapter20

import scala.annotation.tailrec

trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int

  lazy val numer = numerArg / g
  lazy val denom = denomArg / g

  private val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }

  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def toString: String =
    s"$numer/$denom"
}
