package hsu.algorithm.evolutionary

import org.apache.log4j.Logger



abstract class EvolutionaryAlgorithm[T](param: EAParam,
                                   problemFunc: Function1[List[T], Double]){
  private[this] val logger = Logger.getLogger(this.getClass.getName)


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
      logger.info(s"Iteration $i, population size: ${population.size}")

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


