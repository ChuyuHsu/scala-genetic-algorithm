package hsu.algorithm.evolutionary

class OneMax extends BinaryProblem{
  def evaluate(list: List[Boolean]) = list.map{ a => if(a) 1.0 else 0.0}.sum
}

abstract class BinaryProblem extends Problem[Boolean]{
  abstract def evaluate(list: List[Boolean]): Double
}

abstract class Problem[T]{
  abstract def evaluate(list: List[T]):Double
}
