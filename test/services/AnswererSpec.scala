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

    "answer with OUI when asked 'Es tu heureux de participer(OUI/NON)'" in {
      val queryResult = Answerer.answerQuery(Some(mutable.Buffer("Es tu heureux de participer(OUI/NON)")))
      status(queryResult) must equalTo(OK)
      contentAsString(queryResult) must equalTo("OUI")
    }

    "answer with OUI when asked 'Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)'" in {
      val queryResult = Answerer.answerQuery(Some(mutable.Buffer("Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)")))
      status(queryResult) must equalTo(OK)
      contentAsString(queryResult) must equalTo("OUI")
    }

    "answer with NON when asked everything ending by OUI/NON)" in {
      val queryResult = Answerer.answerQuery(Some(mutable.Buffer("azeazezaeOUI/NON)")))
      status(queryResult) must equalTo(OK)
      contentAsString(queryResult) must equalTo("NON")
      val queryResultNotValid = Answerer.answerQuery(Some(mutable.Buffer("azeazOUI/NON)az")))
      status(queryResultNotValid) must equalTo(NOT_FOUND)
    }

    "answer with OUI when asked 'As tu bien recu le premier enonce(OUI/NON)'" in {
      val queryResult = Answerer.answerQuery(Some(mutable.Buffer("As tu bien recu le premier enonce(OUI/NON)")))
      status(queryResult) must equalTo(OK)
      contentAsString(queryResult) must equalTo("OUI")
    }

    "answer with SUM (=2) when asked '1+1'" in {
      val queryResult = Answerer.answerQuery(Some(mutable.Buffer("1 1")))
      status(queryResult) must equalTo(OK)
      contentAsString(queryResult) must equalTo("2")
    }

    "answer with PRODUCT (=1) when asked '1*1'" in {
      val queryResult = Answerer.answerQuery(Some(mutable.Buffer("1*1")))
      status(queryResult) must equalTo(OK)
      contentAsString(queryResult) must equalTo("1")
    }

    "answer with NotFound when asked something else" in {
      val queryResult = Answerer.answerQuery(Some(Seq("Lorem ipsum")))
      status(queryResult) must equalTo(NOT_FOUND)
    }
  }
}
