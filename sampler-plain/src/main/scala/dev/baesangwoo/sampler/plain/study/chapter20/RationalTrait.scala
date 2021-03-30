package dev.baesangwoo.sampler.plain.study.chapter20

import scala.annotation.tailrec

trait RationalTrait {
  val n: Int // 분자
  val d: Int // 분모

  require(d != 0)

  private val g = gcd(n, d)

  val numer = n / g
  val denom = d / g

  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def toString: String =
    s"$numer/$denom"
}

class TwoThirds extends {
  val n = 2
  val d = 3
} with RationalTrait

class RationalClass(n1: Int, d2: Int) extends {
  val n = n1
  val d = d2
} with RationalTrait {
  def + (that: RationalClass) =
    new RationalClass(
      n1 = numer * that.denom + that.numer * denom,
      d2 = that.denom
    )
}

//object RationalTrait extends App {
////  val rational = new RationalTrait {
////    val n: Int = 10
////    val d: Int = 20
////  }
//
//  val RationalTrait = ""
//
//  val dd = RationalTrait
//
//  val rational2 = new {
//    val n = 10
//    val d = 20
//  } with RationalTrait
//
//  println(rational2.toString)
//
//  println(new TwoThirds().toString)
//}
