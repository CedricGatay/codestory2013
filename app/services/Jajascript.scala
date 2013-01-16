package services

import models.Flight
import play.api.Logger
import play.api.libs.json.JsObject
import annotation.tailrec

/**
 * User: cgatay
 * Date: 14/01/13
 * Time: 16:06
 */
object Jajascript {
  type Solution = List[Flight]

  def getSolution(flights: List[Flight]): (Int, Solution) = {
    Logger.info("Trying to compute solutions for : %s".format(flights))
    val sortedFlights = sortFlightsByStartTime(flights)
    val solutions = Jajascript.getMoney(sortedFlights, currentSolution = List())
    val bestSolution = Jajascript.getMostBenefitsSolution(solutions)
    Logger.info("Best solution found : %s".format(bestSolution))
    bestSolution
  }

  def sortFlightsByStartTime(flights: List[Flight]): List[Flight] = {
    flights.sortWith((f1, f2) => f1.startTime <= f2.startTime && f1.cost > f2.cost)
  }

  def getMoney(flights: List[Flight], startTime: Int = 0, currentSolution: Solution = List(), maxGain: Int = 0): List[Solution] = {
    if (flights.isEmpty){
      List(currentSolution)
    }else{
      // explore other solutions
      val moneyWithoutHead: List[Solution] = getMoney(flights.tail, startTime, currentSolution, maxGain)
      val head = flights.head
      if (head.startTime >= startTime) {
        //we can use this flight as a current solution
        val flights1: List[Solution] = getMoney(flights.tail, head.endTime, head :: currentSolution, maxGain + head.cost)
        flights1 ::: moneyWithoutHead
      } else {
        moneyWithoutHead
      }
    }
  }

  def getMostBenefitsSolution(solutions : List[Solution]) : (Int,Solution) = {
    solutions.map(s => (getPotentialGain(s), s)).sortWith((t1, t2) => t1._1 > t2._1).head
  }

  def getPotentialGain(s: Jajascript.Solution): Int = {
    val gain = s.foldLeft(0)((c, f) => c + f.cost)
    gain
  }

  def parseJsonToFlights(objects: Array[JsObject]): List[Flight] = {
    val flights = objects.map(flight => {
      new Flight((flight \ "VOL").as[String],
        (flight \ "DEPART").as[Int],
        (flight \ "DUREE").as[Int],
        (flight \ "PRIX").as[Int])
    })
    flights.toList
  }
}
