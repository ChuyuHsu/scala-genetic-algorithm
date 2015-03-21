package hsu.algorithm.evolutionary

import hsu.algorithm.statistics.Statistics

class Population[T](c: List[Chromosome[T]]){
  val list = c
  val size = c.size

  var best: Int = list.indexOf(list.max)
  def getBestIndividual() = list(best)
  def get(i: Int) = list(i)

  val statistics: Statistics = new Statistics(list.map(_.value));
}


