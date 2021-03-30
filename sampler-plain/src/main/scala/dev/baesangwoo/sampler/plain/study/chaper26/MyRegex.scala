package dev.baesangwoo.sampler.plain.study.chaper26

import scala.util.matching.Regex

object MyRegex {

  val decimal1 = new Regex("(-)?(\\d+)(\\.\\d*)?")
  val decimal2 = new Regex("""(-)?(\d+)(\.\d*)?""")
  val decimal3: Regex = """(-)?(\d+)(\.\d*)?""".r

}
