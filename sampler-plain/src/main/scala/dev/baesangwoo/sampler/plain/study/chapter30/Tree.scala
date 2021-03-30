package dev.baesangwoo.sampler.plain.study.chapter30

trait Tree[+T] {
  def elem: T
  def left: Tree[T]
  def right: Tree[T]
}

object EmptyTree extends Tree[Nothing] {
  override def elem: Nothing = throw new NoSuchElementException("elem")

  override def left: Tree[Nothing] = throw new NoSuchElementException("left")

  override def right: Tree[Nothing] = throw new NoSuchElementException("right")
}

class Branch[+T](override val elem: T, override val left: Tree[T], override val right: Tree[T]) extends Tree[T] {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Branch[_]]

  override def equals(other: Any): Boolean = other match {
    case that: Branch[_] =>
      (that canEqual this) &&
        elem == that.elem &&
        left == that.left &&
        right == that.right
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(elem, left, right)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
