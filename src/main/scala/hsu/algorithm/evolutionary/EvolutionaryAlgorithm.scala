package hsu.algorithm.evolutionary

import org.apache.log4j.Logger
import util.control.Breaks._


abstract class EvolutionaryAlgorithm[T](param: EAParam,
                                        problem: Problem[T]) {
  private[this] val logger = Logger.getLogger(this.getClass.getName)

  protected def initialize(num: Int): Population[T];

  protected def getRandomListOfT(length: Int = param.numOfProblemLength): List[T];

  protected def select(selectionPressure: Int, population: Population[T]): Population[T];

  protected def crossover(population: Population[T]): Population[T];

  protected def mutate(population: Population[T]): Population[T];

  protected def replace(population: Population[T]): Population[T];

  def run() = {
    var population = this.initialize(param.numOfIndividuals)
    breakable {
      for (i <- 0 until param.numOfIterations) {

        logger.info(s"Iteration $i, population size: ${population.size}")

        population = this.select(param.selectionPressure, population)
        population = this.crossover(population)
        population = this.mutate(population)
        population = this.replace(population)

        logger.info("  Best fitness: " + population.getBestIndividual().value)
        logger.info("  pop variance: " + population.statistics.getVariance())
        logger.info("  pop mean: " + population.statistics.getMean())
        logger.info("       individual: " + population.getBestIndividual().genotype)
        if (population.statistics.getVariance() <= 0.25)
          break
      }
    }
    val best = population.getBestIndividual()

    (best.genotype, best.value)
  }

}


