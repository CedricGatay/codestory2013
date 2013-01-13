package models

/**
 * User: cgatay
 * Date: 13/01/13
 * Time: 19:28
 */
class Gross(val amount : Int, val name : String){
  override def toString():String= {
    name + " : " + amount
  }
}
object Foo extends Gross(1, "Foo")
object Bar extends Gross(7, "Bar")
object Qix extends Gross(11, "Qix")
object Baz extends Gross(21, "Baz")
