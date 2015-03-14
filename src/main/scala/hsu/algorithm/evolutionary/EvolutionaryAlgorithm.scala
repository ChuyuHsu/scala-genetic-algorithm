package hsu.algorithm.evolutionary

import scala.collection.mutable.ListBuffer

abstract class EvolutionaryAlgorithm[T](param: EAParam,
                                   problemFunc: Function1[List[T], Double]){
  class Chromosome(val genotype: List[T])
    extends Ordered[Chromosome]
  {
    val value:Double = problemFunc(genotype)

    override def compare(that: Chromosome): Int = this.value compare that.value
  }

  class Population(val size: Int){
    val population_list = this.generate()

    def generate(): List[Chromosome] = {
      val buffer = new ListBuffer[Chromosome]()

      (0 to size).foreach{ i =>
        buffer += new Chromosome(getRandomListOfT())
      }
      buffer.toList
    }

    var best: Int = population_list.indexOf(population_list.max)
    def getBestIndividual() = population_list(best)
  }

  protected def initialize(num: Int) ={
    new Population(num)
  }

  protected def getRandomListOfT(length: Int = param.numOfProblemLength): List[T];
  protected def select(population: Population): Population;
  protected def crossover(population: Population): Population;
  protected def mutate(population: Population): Population;
  protected def replace(population: Population): Population;

  def run(): (List[T], Double) ={
    var population = this.initialize(param.numOfIndividuals)

    (0 to param.numOfIterations).foreach { i =>
      println(s"Iteration $i")
      population = this.select(population)
      population = this.crossover(population)
      population = this.mutate(population)
      population = this.replace(population)

      println("  Best individual: " + population.getBestIndividual().genotype)
    }
    val best = population.getBestIndividual()

    (best.genotype, best.value)
  }

}


