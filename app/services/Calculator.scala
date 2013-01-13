package services

/**
 * User: cgatay
 * Date: 13/01/13
 * Time: 22:59
 */
abstract class Operation(val symbol : String){
  def compute(operand1:String, operand2:String):String
}
case class Add() extends Operation("+"){
  def compute(operand1: String, operand2: String): String = {
    (operand1.toFloat + operand2.toFloat).toString
  }
}
object Calculator {

}
