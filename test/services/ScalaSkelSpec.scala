package services

import org.specs2.mutable._
import play.api.test.Helpers._
import collection.mutable
import models._


/**
 * User: cgatay
 * Date: 08/01/13
 * Time: 09:18
 */
class ScalaSkelSpec extends Specification {
  "The Change" should {
    "return foo : 1 when asked to convert 1" in {
      val queryResult = ScalaSkel.gimmeChange(1)
      queryResult.size must equalTo(1)
      queryResult.head must equalTo(Map(Foo -> 1))
    }
    "return foo : 7, bar : 1 when asked to convert 7" in {
      val queryResult = ScalaSkel.gimmeChange(7)
      queryResult.size must equalTo(2)
      queryResult must equalTo(List(Map(Bar -> 1), Map(Foo -> 7)))
    }
    "return [qix : 1], [foo : 4, bar : 1], [foo : 11] when asked to convert 11" in {
      val queryResult = ScalaSkel.gimmeChange(11)
      queryResult.size must equalTo(3)
      queryResult must equalTo(List(Map(Qix -> 1), Map(Bar -> 1, Foo -> 4), Map(Foo -> 11)))
    }
    "return [baz : 1], [foo : 10, qix : 1], [foo : 3, bar : 1, qix : 1] [qix:1, bar: 1, foo:3] [foo : 7, bar: 2] [foo : 14, bar:1] [bar:3] when asked to convert 21" in {
      val queryResult = ScalaSkel.gimmeChange(21)
      queryResult.size must equalTo(7)
      queryResult must equalTo(List(Map(Baz -> 1),
                                    Map(Bar -> 3),
                                    Map(Qix -> 1, Bar -> 1, Foo -> 3),
                                    Map(Bar -> 2, Foo -> 7),
                                    Map(Qix -> 1, Foo -> 10),
                                    Map(Bar -> 1, Foo -> 14),
                                    Map(Foo -> 21)))
    }
  }
}
