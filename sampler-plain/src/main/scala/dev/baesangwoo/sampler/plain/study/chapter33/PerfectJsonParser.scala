package dev.baesangwoo.sampler.plain.study.chapter33

import scala.util.parsing.combinator.JavaTokenParsers

class PerfectJsonParser extends JavaTokenParsers {

//  def obj: Parser[Map[String, Any]] = "{" ~> repsep(member, ",") <~ "}" ^^ (Map() ++ _)
//
//  def arr: Parser[List[Any]] = "[" ~> repsep(value, ",") <~ "]"
//
//  def member = stringLiteral ~ ":" ~ value ^^ { case name ~ ":" ~ value => (name, value) }
//
//  def value: Parser[Any] = (
//    obj
//      | arr
//      | stringLiteral
//      | floatingPointNumber ^^ (_.toDouble)
//      | "null" ^^ (_ => null)
//      | "true" ^^ (_ => true)
//      | "false" ^^ (_ => false)
//  )
}
