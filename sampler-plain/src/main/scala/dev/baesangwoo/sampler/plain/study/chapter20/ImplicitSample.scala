package dev.baesangwoo.sampler.plain.study.chapter20

object ImplicitSample extends App {
  def myTest[T](elements: List[T])(implicit ordering: Ordering[T]): T = {
    elements match {
      case List()  => throw new RuntimeException("list is empty")
      case List(x) => x
      case x :: rest =>
        val maxRest = myTest(rest)
        //        if (ordering.gt(x, maxRest)) x
        if (implicitly[Ordering[T]].gt(x, maxRest)) x
        else maxRest
    }
  }

  def maxList[T: Ordering](elements: List[T]): T = {
    elements match {
      case List()  => throw new RuntimeException("list is empty")
      case List(x) => x
      case x :: rest =>
        val maxRest = myTest(rest)
        //        if (ordering.gt(x, maxRest)) x
        if (implicitly[Ordering[T]].gt(x, maxRest)) x
        else maxRest
    }
  }

  println(myTest(List(1, 2, 4, 1, 20)))
  println(maxList(List(1, 2, 4, 1, 20)))

  implicit def intToRange(i: Int): Range.Inclusive = 1 to i
//  implicit def intToDigits(i: Int): Seq[Int] = i.toString.toList.map(_.toInt)

  def printLength(seq: Seq[Int]): Unit = println(seq.length)

  printLength(12)
}
