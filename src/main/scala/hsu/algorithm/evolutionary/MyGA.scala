package hsu.algorithm.evolutionary

import org.apache.log4j.Logger

object MyGA {
  val logger = Logger.getLogger(MyGA.getClass)

  def main(args: Array[String]) {
    logger.info("Start MyGA...")
    val param = EAParam(4000, 40, 100, 3, 0.5, 0.0)
    val problem = new OneMax();

    val ga = new GeneticAlgorithm(param, problem)
    val (solution, fitness) = ga.run()
    println(s"Solution found: $solution, \nFitness: $fitness")
  }
}