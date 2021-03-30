package dev.baesangwoo.sampler.plain.study.solo

import org.scalatest.PrivateMethodTester
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PartialFunctionSample {

  private def getDescription: PartialFunction[Int, String] = {
    case 1 => "one"
    case 2 => "two"
    case _ => "other"
  }

  private def getData(i: Int): String = {
    i match {
      case 1 => "one"
      case 2 => "two"
      case _ => "other"
    }
  }

}

class PartialFunctionSampleTest extends AnyWordSpec with Matchers with PrivateMethodTester {

  private val sample = new PartialFunctionSample

  "getDescription" must {

    "test default" in {
      val funGetData = PrivateMethod[String](Symbol("getData"))
      sample invokePrivate funGetData(1) mustBe "one"
    }

    "test partial function 1" in {
      val funGetDescription1 = PrivateMethod[String](Symbol("getDescription"))
      sample invokePrivate funGetDescription1(1) mustBe "one"
    }

    "test partial function 2" in {
      val funGetDescription2 = PrivateMethod[PartialFunction[Int, String]](Symbol("getDescription"))
      sample invokePrivate funGetDescription2(1) mustBe "one"
    }
  }

}
