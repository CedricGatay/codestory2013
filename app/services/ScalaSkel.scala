package services

/**
 * User: cgatay
 * Date: 13/01/13
 * Time: 19:04
 */
class ScalaSkel {

  def countChange(grossDessimal: Int, coins: List[Int]): Int = {
    if (grossDessimal == 0) 1
    else if (grossDessimal < 0) 0
    else if (!coins.isEmpty) countChange(grossDessimal, coins.tail) + countChange(grossDessimal - coins.head, coins)
    else 0
  }

}
