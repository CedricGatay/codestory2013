package models

/**
 * User: cgatay
 * Date: 14/01/13
 * Time: 16:04
 */
class Flight(val name : String, val  startTime : Int, val duration : Int, val cost : Int) {
  val endTime = startTime + duration

  override def toString : String = {
    name
  }
}
