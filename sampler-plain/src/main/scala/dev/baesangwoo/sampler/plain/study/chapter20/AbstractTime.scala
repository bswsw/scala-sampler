package dev.baesangwoo.sampler.plain.study.chapter20

trait AbstractTime {
  var hour: Int
  var min: Int
}

class DefaultTime extends AbstractTime {
  override var hour: Int = 10
  override var min: Int = 20
}
