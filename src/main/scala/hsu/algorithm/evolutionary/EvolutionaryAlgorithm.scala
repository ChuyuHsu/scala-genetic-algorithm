package hsu.algorithm.evolutionary

import org.apache.log4j.Logger



abstract class EvolutionaryAlgorithm[T](param: EAParam,
                                   problemFunc: Function1[List[T], Double]){
  private[this] val logger = Logger.getLogger(this.getClass.getName)

  class Chromosome(val genotype: List[T])
    extends Ordered[Chromosome]
  {
    val value:Double = problemFunc(genotype)

    override def compare(that: Chromosome): Int = this.value compare that.value
  }

  class Population(s: Int, c: List[Chromosome]){
    def this(s: Int) = this(s, Population.generate(s))
    def this(c: List[Chromosome]) = this(c.size, c)

    val list = c
    val size = s

    var best: Int = list.indexOf(list.max)
    def getBestIndividual() = list(best)
    def get(i: Int) = list(i)
  }

  object Population{
    def generate(size: Int): List[Chromosome] = {
      (0 to size).map{ i =>
        new Chromosome(getRandomListOfT())
      }.toList
    }
  }

  protected def initialize(num: Int) ={
    new Population(num)
  }

  protected def getRandomListOfT(length: Int = param.numOfProblemLength): List[T];
  protected def select(selectionPressure: Int, population: Population): Population;
  protected def crossover(population: Population): Population;
  protected def mutate(population: Population): Population;
  protected def replace(population: Population): Population;

  def run(): (List[T], Double) ={
    var population = this.initialize(param.numOfIndividuals)

    (0 to param.numOfIterations).foreach { i =>
      logger.info(s"Iteration $i")
      population = this.select(param.selectionPressure, population)
      population = this.crossover(population)
      population = this.mutate(population)
      population = this.replace(population)

      logger.info("  Best fitness: " + population.getBestIndividual().value)
      logger.info("       individual: " + population.getBestIndividual().genotype)
    }
    val best = population.getBestIndividual()

    (best.genotype, best.value)
  }

}


