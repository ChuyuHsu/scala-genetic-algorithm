package hsu.algorithm.evolutionary

import scala.util.Random
import org.apache.log4j.Logger

class GeneticAlgorithm(param: EAParam,
                       problemFunc: Function1[List[Boolean], Double])
  extends EvolutionaryAlgorithm[Boolean](param, problemFunc)
{
  private[this] val logger = Logger.getLogger(this.getClass.getName)

  override protected def initialize(num: Int): Population = {
    return new Population(param.numOfIndividuals)
  }

  override protected def getRandomListOfT(length: Int) = List.fill(length)(Random.nextBoolean())

  override protected def select(selectionPressure: Int, population: Population) = {
    //tournamentSelection
    val nextGen = population.size

    val randomIndices = (for(i <- 0 until selectionPressure) yield Random.shuffle( (0 until nextGen).toList )).flatten.toList

    new Population((0 until nextGen).map{ i =>
      (0 until selectionPressure).map { case j =>
        population.get(randomIndices(selectionPressure * i + j))}.max
    }.toList)
  }

  override protected def crossover(population: Population) = {
    val nextGen = population.size
    val randomIndices = (for(i <- 0 until 2) yield Random.shuffle( (0 until nextGen).toList )).flatten.toList

    val parentList = (0 until nextGen).map{ i =>
      (0 until 2).map { case j =>
        population.get(randomIndices(2 * i + j)).genotype}
    }

    val offspring = parentList.map{ parents =>
      if(param.crossoverRate >= Random.nextDouble()){
        //crossover
        val cutPoint = Random.nextInt()
        val child1 = parents(0).slice(0, cutPoint) ::: parents(1).slice(cutPoint, parents(1).size)
        val child2 = parents(1).slice(0, cutPoint) ::: parents(0).slice(cutPoint, parents(0).size)

        logger.trace("    parent0: " + parents(0).toString())
        logger.trace("    parent1: " + parents(1).toString())
        logger.trace("    child1: " + child1.toString())
        logger.trace("    child2: " + child2.toString())

        List(child1, child2)
      }else
        parents
    }.flatten.map(new Chromosome(_) ).toList

    new Population(offspring)
  }

  override protected def replace(population: Population) = {
    population
  }

  override protected def mutate(population: Population) = {
    new Population(population.list.map{ chromosome =>
      if(param.mutationRate >= Random.nextDouble()){
        val i = Random.nextInt(chromosome.genotype.size)
        logger.trace(s"  Mutated at $i")
        new Chromosome(chromosome.genotype.updated(i, !chromosome.genotype(i)))
      }else
        chromosome
    })
  }
}


