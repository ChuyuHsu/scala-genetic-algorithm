import scala.collection.mutable.ListBuffer
import scala.util.Random

abstract class EvolutionaryAlgorithm[T](param: EAParam,
                                   problemFunc: Function1[List[T], Double]){
  class Chromosome(val genotype: List[T]){
    val value:Double = problemFunc(genotype)
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
  }

  def initialize(num: Int): Population ={
    new Population(num)
  }

  def getRandomListOfT(length: Int = param.numOfProblemLength): List[T];
  def select(population: Population): Population;
  def crossover(population: Population): Population;
  def mutate(population: Population): Population;
  def replace(population: Population): Population;

  def run(): Unit ={
    var population = this.initialize(param.numOfIndividuals)

    (0 to param.numOfIterations).foreach { i =>
      println(s"Iteration $i")
      population = this.select(population)
      population = this.crossover(population)
      population = this.mutate(population)
      population = this.replace(population)
    }
  }

}


