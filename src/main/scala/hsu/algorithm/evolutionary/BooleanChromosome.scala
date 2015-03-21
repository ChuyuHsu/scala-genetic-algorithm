package hsu.algorithm.evolutionary

class BooleanChromosome(l: List[Boolean], p: BinaryProblem) extends Chromosome[Boolean](l, p)
{
  override val genotype: List[Boolean] = l
  override protected val problem: BinaryProblem = p

  val value: Double = problem.evaluate(genotype)

  override def compare(that: Chromosome[Boolean]): Int = this.value compare that.value
}
