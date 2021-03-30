package dev.baesangwoo.sampler.plain.study.chapter33

import scala.util.parsing.combinator.JavaTokenParsers

class JsonParser extends JavaTokenParsers {

  def value: Parser[Any] = obj | arr | stringLiteral | floatingPointNumber | "null" | "true" | "false"

  def obj: Parser[Any] = "{" ~ repsep(member, ",") ~ "}"

  def arr: Parser[Any] = "[" ~ repsep(value, ",") ~ "}"

  def member: Parser[Any] = stringLiteral ~ ":" ~ value

}
