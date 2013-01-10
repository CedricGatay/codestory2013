package services

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import collection.mutable


/**
 * User: cgatay
 * Date: 08/01/13
 * Time: 09:18
 */
class AnswererSpec extends Specification {
  "The Answerer" should {
    "answer with cedric@gatay.fr when asked 'Quelle est ton adresse email'" in {
      val queryResult = Answerer.answerQuery(Some(Seq("Quelle est ton adresse email")))
      status(queryResult) must equalTo(OK)
      contentAsString(queryResult) must equalTo("cedric@gatay.fr")
    }

    "answer with OUI when asked 'Es tu abonne a la mailing list(OUI/NON)'" in {
      val queryResult = Answerer.answerQuery(Some(mutable.Buffer("Es tu abonne a la mailing list(OUI/NON)")))
      status(queryResult) must equalTo(OK)
      contentAsString(queryResult) must equalTo("OUI")
    }

    "answer with NotFound when asked something else" in {
      val queryResult = Answerer.answerQuery(Some(Seq("Lorem ipsum")))
      status(queryResult) must equalTo(NOT_FOUND)
    }
  }
}
