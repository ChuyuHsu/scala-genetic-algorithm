package hsu.algorithm.evolutionary

import scala.util.Random

class GeneticAlgorithm(param: EAParam,
                       problemFunc: Function1[List[Boolean], Double])
  extends EvolutionaryAlgorithm[Boolean](param, problemFunc)
{
  override def initialize(num: Int): Population = {
    return new Population(param.numOfIndividuals)
  }

  def getRandomListOfT2(length: Int): List[Boolean] = List.fill(3)(Random.nextBoolean())

  override def getRandomListOfT(length: Int): List[Boolean] = {
    List.fill(3)(Random.nextBoolean()).toList
  }

  override def crossover(population: Population): Population = {
    population
  }

  override def replace(population: Population): Population = {
    population
  }

  override def select(population: Population): Population = {
    population
  }

  override def mutate(population: Population): Population = {
    population
  }
}
