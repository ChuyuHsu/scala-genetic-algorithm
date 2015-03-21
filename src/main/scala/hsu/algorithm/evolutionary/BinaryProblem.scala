package hsu.algorithm.evolutionary

class OneMax extends BinaryProblem{
  def evaluate(list: List[Boolean]) = list.map{ a => if(a) 1.0 else 0.0}.sum
}

abstract class BinaryProblem extends Problem[Boolean]{
  def evaluate(list: List[Boolean]): Double
}

abstract class Problem[T]{
  def evaluate(list: List[T]):Double
}
