import scala.util.Random

class GeneticAlgorithm[Boolean](param: EAParam,
                       problemFunc: Function1[List[Boolean], Double])
  extends EvolutionaryAlgorithm(param, problemFunc)
{
  override def initialize(num: Int): Population = {
    return new Population(param.numOfIndividuals)
  }


  override def getRandomListOfT(length: Int): List[Boolean] = {
    List.fill(3)(Random.nextBoolean())
  }

  override def crossover(population: Population): Population = ???

  override def replace(population: Population): Population = ???

  override def select(population: Population): Population = ???

  override def mutate(population: Population): Population = ???
}
