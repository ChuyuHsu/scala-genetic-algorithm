package hsu.algorithm.evolutionary

class Population(c: List[Chromosome]){
  val list = c

  var best: Int = list.indexOf(list.max)
  def getBestIndividual() = list(best)
  def get(i: Int) = list(i)
}


