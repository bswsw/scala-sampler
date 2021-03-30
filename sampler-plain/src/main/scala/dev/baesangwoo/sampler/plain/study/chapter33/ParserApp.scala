package dev.baesangwoo.sampler.plain.study.chapter33

object ParserApp extends Arith {

  def main(args: Array[String]): Unit = {
    val data = "2 * (3 - 7)"

    println(parseAll(expr, data))
  }

}
