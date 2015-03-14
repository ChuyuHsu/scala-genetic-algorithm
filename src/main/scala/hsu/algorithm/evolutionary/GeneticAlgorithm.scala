package hsu.algorithm.evolutionary

import scala.util.Random



class GeneticAlgorithm(param: EAParam,
                       problemFunc: Function1[List[Boolean], Double])
  extends EvolutionaryAlgorithm[Boolean](param, problemFunc)
{
  override protected def initialize(num: Int): Population = {
    return new Population(param.numOfIndividuals)
  }

  override protected def getRandomListOfT(length: Int) = List.fill(length)(Random.nextBoolean())

  override protected def crossover(population: Population) = {
    population
  }

  override protected def replace(population: Population) = {
    population
  }

  override protected def select(population: Population) = {
    population
  }

  override protected def mutate(population: Population) = {
    population
  }
}


