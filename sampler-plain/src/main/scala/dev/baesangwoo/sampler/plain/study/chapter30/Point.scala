package dev.baesangwoo.sampler.plain.study.chapter30

import dev.baesangwoo.sampler.plain.study.chapter20.Color

import scala.collection.mutable

class Point(val x: Int, val y: Int) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Point]

  override def equals(other: Any): Boolean = other match {
    case that: Point =>
      (that canEqual this) &&
        x == that.x &&
        y == that.y
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(x, y)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

class Color extends Enumeration {
  val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
}

class ColoredPoint(x: Int, y: Int, val color: Color.Value) extends Point(x, y) {

  override def canEqual(other: Any): Boolean = other.isInstanceOf[ColoredPoint]

  override def equals(other: Any): Boolean = other match {
    case that: ColoredPoint =>
      super.equals(that) &&
        (that canEqual this) &&
        color == that.color
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(super.hashCode(), color)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  //  def canEqual(other: Any): Boolean = other.isInstanceOf[ColoredPoint]
  //
  //  override def equals(other: Any): Boolean = other match {
  //    case that: ColoredPoint =>
  //      (that canEqual this) &&
  //        color == that.color
  //    case _ => false
  //  }
  //
  //  override def hashCode(): Int = {
  //    val state = Seq(color)
  //    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  //  }

}

object Point extends App {

  val m = new mutable.HashSet[Point]()
  val p = new Point(1, 2)

  m.add(p)

  println(m contains p)

  println(m contains p)
}
