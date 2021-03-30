package cat

import cats.Monoid
import cats.implicits.{catsKernelStdGroupForInt, catsKernelStdMonoidForOption, catsKernelStdMonoidForString, catsKernelStdSemilatticeForSet, catsSyntaxSemigroup}

object MonoidApp extends App {

  val str1 = "one" ++ "two"
  val str2 = "one" + "two"
  val str3 = "one".+("two")

  implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

//  implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
//    override def empty: Boolean = false
//    override def combine(x: Boolean, y: Boolean): Boolean = x || y
//  }
//
//  implicit val booleanEitherMonoid: Monoid[Boolean] = new Monoid[Boolean] {
//    override def empty: Boolean = false
//    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
//  }

//  implicit def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
//    override def empty: Set[A] = Set.empty[A]
//    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
//  }

  Monoid[Boolean].combine(false, true)
  Monoid[Set[Int]].combine(Set(1, 2), Set(3, 4))
  Monoid[String].combine("hello", "world")

  val stringResult = "Hi" |+| "there"

  def add[A](items: List[A])(implicit monoid: Monoid[A]): A = {
    items.foldLeft(monoid.empty)(_ |+| _)
  }

  def add2[A: Monoid](items: List[A]): A = {
    items.foldLeft(Monoid[A].empty)(_ |+| _)
  }

  println(add(List(1, 2, 3)))
  println(add2(List(1, 2, 3)))
  println(add2(List(None, Some(1), None, Some(4))))
//  add(List(Some(1), Some(2), Some(3)))

  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0, 0)
    override def combine(x: Order, y: Order): Order = {
      Order(
        cost = x.cost + y.cost,
        quantity = x.quantity + y.quantity
      )
    }
  }
}

case class Order(cost: Double, quantity: Double)
