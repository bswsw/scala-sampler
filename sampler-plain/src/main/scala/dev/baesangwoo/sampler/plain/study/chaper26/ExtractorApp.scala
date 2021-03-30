package dev.baesangwoo.sampler.plain.study.chaper26

import dev.baesangwoo.sampler.plain.Hr
import dev.baesangwoo.sampler.plain.study.chaper26.MyRegex.decimal1

object ExtractorApp extends App {

  def matchEmail(str: String): Unit = {
    str match {
      case Email(user, Domain(last @ "com", domain @ _*)) => println(s"this is email ($user, $last, $domain)")
      case _ @p                                           => println(s"this is not email : $p")
    }
  }

  matchEmail("sangwoo@aaa.com")
  matchEmail("sangwoo@aaa.net")
  matchEmail("sadadd")
  matchEmail("asdada!aaa.com")

  Email.unapply(Email.apply("sss", "aaa"))

  Hr.print()

  val input = "for -1.0 to 99 by 3"

  for (s <- MyRegex.decimal1 findAllIn input) {
    println(s)
  }

  val Decimal = """(-)?(\d+)(\.\d*)?""".r

  val Decimal(sign, inter, dec) = "-1.23"

  val decimal1(a, s, d) = "-1.23"

  println(a)

  val decimal1(a1, s1, d1) = "1.23"

  println(a1)
}
