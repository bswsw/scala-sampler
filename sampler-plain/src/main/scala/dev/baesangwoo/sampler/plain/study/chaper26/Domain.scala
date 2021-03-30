package dev.baesangwoo.sampler.plain.study.chaper26

object Domain {

  def unapplySeq(str: String): Option[Seq[String]] = {
    Some(str.split("""\.""").reverse)
  }

}
