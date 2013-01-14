package services

import models.Flight
import play.api.Logger

/**
 * User: cgatay
 * Date: 14/01/13
 * Time: 16:06
 */
object Jajascript {
  type Solution = List[Flight]

  def getSolution(flights : Seq[Flight]) : (Int, Solution) = {
    Logger.info("Trying to compute solutions for : %s".format(flights))
    val sortedFlights = sortFlightsByStartTime(flights)
    val solutions = Jajascript.getMoney(sortedFlights, currentSolution = List())
    val bestSolution = Jajascript.getMostBenefitsSolution(solutions)
    Logger.info("Best solution found : %s".format(bestSolution))
    bestSolution
  }

  def sortFlightsByStartTime(flights : Seq[Flight]) : Seq[Flight] = {
    flights.sortWith((f1, f2) => f1.startTime <= f2.startTime)
  }

  def getMoney(flights : Seq[Flight], startTime : Int = 0, currentSolution : Solution = List()) : List[Solution] = {
    if (flights.isEmpty){
      List(currentSolution)
    }else{
      // explore other solutions
      val moneyWithoutHead : List[Solution] = getMoney(flights.tail, startTime, currentSolution)
      val head = flights.head
      if (head.startTime >= startTime){
        //we can use this flight as a current solution
        val flights1:List[Solution] = getMoney(flights.tail, head.endTime, head::currentSolution)
        flights1 ::: moneyWithoutHead
      }else{
        moneyWithoutHead
      }
    }
  }

  def getMostBenefitsSolution(solutions : List[Solution]) : (Int,Solution) = {
    solutions.map(s => (s.foldLeft(0)((c, f) => c + f.cost), s)).sortWith((t1, t2) => t1._1 > t2._1).head
  }
}
