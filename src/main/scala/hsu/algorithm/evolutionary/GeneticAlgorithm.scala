package hsu.algorithm.evolutionary

import scala.util.Random
import org.apache.log4j.Logger

class GeneticAlgorithm(param: EAParam,
                       problemFunc: Function1[List[Boolean], Double])
  extends EvolutionaryAlgorithm[Boolean](param, problemFunc)
{
  private[this] val logger = Logger.getLogger(this.getClass.getName)

  override protected def getRandomListOfT(length: Int) = List.fill(length)(Random.nextBoolean())

  override protected def select(selectionPressure: Int, population: Population) = {
    //tournamentSelection
    val nextGen = population.size

        val randomIndices = (for(i <- 0 until selectionPressure) yield Random.shuffle( (0 until nextGen).toList )).flatten.toList

        new Population((0 until nextGen).map{ i =>
          logger.trace("selection candidates:")
          (0 until selectionPressure).map { j =>
            logger.trace(s"    index: ${selectionPressure * i + j}, randomIndex: ${randomIndices(selectionPressure * i + j)}")
            logger.trace(s"    value: ${population.get(randomIndices(selectionPressure * i + j)).genotype}")
            population.get(randomIndices(selectionPressure * i + j))}.max
        }.toList)
    population
  }

  override protected def crossover(population: Population) = {
    val nextGen = population.size
    val randomIndices = Random.shuffle( (0 until nextGen).toList )

    val parentList = (0 until nextGen/2).map{ i =>
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
//        logger.trace("    child1: " + child1.toString())
//        logger.trace("    child2: " + child2.toString())

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
    new Population(population.list.map { chromosome =>
      new Chromosome(chromosome.genotype.map(b => if (param.mutationRate >= Random.nextDouble()) !b else b))
    })
  }
}


