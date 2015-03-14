package hsu.algorithm.evolutionary

object MyGA {
  //  val logger = Logger.getLogger(GeneticAlgorithm.getClass.getName)

  def main(args: Array[String]) {
    val param = EAParam(100, 1000, 10)
    val problem = BinaryProblem.oneMax(param.numOfProblemLength, _:List[Boolean])

    val ga = new GeneticAlgorithm(param, problem)
    val (solution, fitness) = ga.run()
    println(s"Solution found: $solution, \nFitness: $fitness")
  }
}