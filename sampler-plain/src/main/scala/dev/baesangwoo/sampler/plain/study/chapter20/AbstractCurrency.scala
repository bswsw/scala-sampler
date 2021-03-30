package dev.baesangwoo.sampler.plain.study.chapter20

trait AbstractCurrency {
  type Currency <: AbstractCurrency

  def make(amount: Int): Currency

  val amount: Long
  val design: String

  override def toString: String =
    s"$amount $design"

//  def +(that: Currency): Currency = new Currency {
//    override val amount: Long = this.amount + that.amount
//  }
}
