package services

import models._
import models.Baz
import models.Qix
import models.Foo

/**
 * User: cgatay
 * Date: 13/01/13
 * Time: 19:04
 */
object ScalaSkel {

  def gimmeChange(groDessimal : Int) : List[Map[Gross, Int]] = {
    val change = countChange(groDessimal, List(Foo, Bar, Qix, Baz), List())
    val toMap = change.map(s => s.map(g => (g, s.count(_ == g))).toMap)
    toMap
  }

  private def countChange(groDessimal: Int, coins: List[Gross], acc: List[Gross]): List[List[Gross]]= {
    if (groDessimal == 0) List(acc)
    else if (groDessimal < 0) List()
    else if (!coins.isEmpty) {
      val tailChange = countChange(groDessimal, coins.tail, acc)
      val nextValue = groDessimal - coins.head.amount
      if (nextValue >= 0){
        val headChange = countChange(nextValue, coins, coins.head :: acc)
        tailChange ::: headChange
      }else{
        tailChange
      }
    } else List()
  }

}
