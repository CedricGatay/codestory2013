package services

import org.specs2.mutable._
import models._
import play.api.libs.json.{JsObject, Json}


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
      val money = Jajascript.getMoney(sortedFlights)
      val bestSolution = Jajascript.getMostBenefitsSolution(money)

      bestSolution._1 must equalTo(18)
      bestSolution._2 must equalTo(List(legacy01, monad42))
    }
    "return 40 : [straight-tailspin-54, relieved-album-92, short-backpack-81] for query 2" in{
      val json = Json.parse( """[
                               |{"VOL":"short-backpack-81","DEPART":0,"DUREE":4,"PRIX":12},
                               |{"VOL":"better-stairwell-58","DEPART":1,"DUREE":2,"PRIX":4},
                               |{"VOL":"purring-license-70","DEPART":2,"DUREE":6,"PRIX":4},
                               |{"VOL":"relieved-album-92","DEPART":4,"DUREE":5,"PRIX":14},
                               |{"VOL":"short-joiner-41","DEPART":5,"DUREE":2,"PRIX":3},
                               |{"VOL":"shy-warlord-28","DEPART":5,"DUREE":4,"PRIX":14},
                               |{"VOL":"lovely-skinhead-19","DEPART":6,"DUREE":2,"PRIX":5},
                               |{"VOL":"brainy-saleswoman-54","DEPART":7,"DUREE":6,"PRIX":1},
                               |{"VOL":"straight-tailspin-54","DEPART":9,"DUREE":5,"PRIX":14},
                               |{"VOL":"quaint-gunslinger-59","DEPART":10,"DUREE":2,"PRIX":7}]""".stripMargin)
      val flights = Jajascript.parseJsonToFlights(json.as[Array[JsObject]])
      flights.length must equalTo(10)
      val solution = Jajascript.getSolution(flights)
      solution._1 must equalTo(40)
      solution._2.length must equalTo(3)
      solution._2 must containAllOf(List(flights(8), flights(3), flights(0)))
    }

    "return 57 for query 3" in {
      val json = Json.parse("""[
                              |{"VOL":"weary-tonguelashing-93","DEPART":0,"DUREE":4,"PRIX":7},
                              |{"VOL":"better-rifleman-93","DEPART":1,"DUREE":2,"PRIX":8},
                              |{"VOL":"worried-stagehand-68","DEPART":2,"DUREE":6,"PRIX":6},
                              |{"VOL":"skinny-sextant-27","DEPART":4,"DUREE":5,"PRIX":23},
                              |{"VOL":"worried-plank-48","DEPART":5,"DUREE":2,"PRIX":13},
                              |{"VOL":"brave-tentacle-17","DEPART":5,"DUREE":4,"PRIX":6},
                              |{"VOL":"dull-toadstool-6","DEPART":6,"DUREE":2,"PRIX":9},
                              |{"VOL":"puny-duct-72","DEPART":7,"DUREE":6,"PRIX":4},
                              |{"VOL":"graceful-storefront-3","DEPART":9,"DUREE":5,"PRIX":20},
                              |{"VOL":"weary-gerbil-48","DEPART":10,"DUREE":2,"PRIX":19},
                              |{"VOL":"ancient-visa-51","DEPART":10,"DUREE":4,"PRIX":7},
                              |{"VOL":"fantastic-headache-39","DEPART":11,"DUREE":2,"PRIX":7},
                              |{"VOL":"enthusiastic-panther-55","DEPART":12,"DUREE":6,"PRIX":7},
                              |{"VOL":"Early-skylight-93","DEPART":14,"DUREE":5,"PRIX":6},
                              |{"VOL":"hilarious-sewer-75","DEPART":15,"DUREE":2,"PRIX":5}
                              |]""".stripMargin)
      val flights = Jajascript.parseJsonToFlights(json.as[Array[JsObject]])
      val solution = Jajascript.getSolution(flights)
      solution._1 must equalTo(57)
      solution._2.length must equalTo(4)
    }
    "return 92 for query 4" in {
      val json = Json.parse("""[
                              |{"VOL":"adorable-board-93","DEPART":0,"DUREE":4,"PRIX":12},
                              |{"VOL":"blue-wart-32","DEPART":1,"DUREE":2,"PRIX":7},
                              |{"VOL":"real-tablespoon-20","DEPART":2,"DUREE":6,"PRIX":1},
                              |{"VOL":"spotless-malaria-71","DEPART":4,"DUREE":5,"PRIX":10},
                              |{"VOL":"smiling-sandbox-29","DEPART":5,"DUREE":2,"PRIX":25},
                              |{"VOL":"better-castle-90","DEPART":5,"DUREE":4,"PRIX":13},
                              |{"VOL":"silly-gravy-91","DEPART":6,"DUREE":2,"PRIX":2},
                              |{"VOL":"horrible-autograph-87","DEPART":7,"DUREE":6,"PRIX":2},
                              |{"VOL":"fat-festival-73","DEPART":9,"DUREE":5,"PRIX":15},
                              |{"VOL":"agreeable-blonde-36","DEPART":10,"DUREE":2,"PRIX":21},
                              |{"VOL":"whispering-streetcar-70","DEPART":10,"DUREE":4,"PRIX":10},
                              |{"VOL":"scary-suffix-74","DEPART":11,"DUREE":2,"PRIX":1},
                              |{"VOL":"naughty-weight-11","DEPART":12,"DUREE":6,"PRIX":7},
                              |{"VOL":"foolish-berry-9","DEPART":14,"DUREE":5,"PRIX":15},
                              |{"VOL":"dull-tangelo-73","DEPART":15,"DUREE":2,"PRIX":5},
                              |{"VOL":"blushing-gateway-21","DEPART":15,"DUREE":4,"PRIX":12},
                              |{"VOL":"combative-muscle-90","DEPART":16,"DUREE":2,"PRIX":2},
                              |{"VOL":"innocent-compass-13","DEPART":17,"DUREE":6,"PRIX":6},
                              |{"VOL":"busy-movement-85","DEPART":19,"DUREE":5,"PRIX":10},
                              |{"VOL":"wicked-jaw-1","DEPART":20,"DUREE":2,"PRIX":19}
                              |]""".stripMargin)
      val flights = Jajascript.parseJsonToFlights(json.as[Array[JsObject]])
      val solution = Jajascript.getSolution(flights)
      solution._1 must equalTo(92)
      solution._2.length must equalTo(5)
    }
  }


}
