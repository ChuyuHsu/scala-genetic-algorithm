package hsu.algorithm.evolutionary

object BinaryProblem {
  def oneMax(length: Int, list: List[Boolean]): Double = list.count(_ == true)
}
