package services

import org.specs2.mutable._
import models._


/**
 * User: cgatay
 * Date: 08/01/13
 * Time: 09:18
 */
class JajascriptSpec extends Specification {
  "The Jajascript" should {
    "return [\"MONAD42\",\"LEGACY01\"] when asked [\n \t\t{ \"VOL\": \"MONAD42\", \"DEPART\": 0, \"DUREE\": 5, \"PRIX\": 10 },\n" +
      " \t\t{ \"VOL\": \"META18\", \"DEPART\": 3, \"DUREE\": 7, \"PRIX\": 14 },\n" +
      " \t\t{ \"VOL\": \"LEGACY01\", \"DEPART\": 5, \"DUREE\": 9, \"PRIX\": 8 },\n" +
      " \t\t{ \"VOL\": \"YAGNI17\", \"DEPART\": 5, \"DUREE\": 9, \"PRIX\": 7 }\n \t]" in {
      val legacy01 = new Flight("LEGACY01", 5, 9, 8)
      val monad42 = new Flight("MONAD42", 0, 5, 10)
      val flights = List(monad42, new Flight("META18", 3, 7, 14), legacy01, new Flight("YAGNI17", 5, 9, 7))
      val sortedFlights = Jajascript.sortFlightsByStartTime(flights)
      val money = Jajascript.getMoney(sortedFlights, currentSolution = List())
      val bestSolution = Jajascript.getMostBenefitsSolution(money)

      bestSolution._1 must equalTo(18)
      bestSolution._2 must equalTo(List(legacy01, monad42))
    }
  }
}
