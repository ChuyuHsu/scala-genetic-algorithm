package hsu.algorithm.evolutionary


class BooleanChromosome(l: List[Boolean], p: BinaryProblem) extends Chromosome[Boolean](l, p)
{
  override val genotype: List[Boolean] = l
  override protected val problem: BinaryProblem = p

  val value: Double = problem.evaluate(genotype)

  override def compare(that: Chromosome): Int = this.value compare that.value
}

abstract class Chromosome[T](l: List[T], p: Problem)
  extends Ordered[Chromosome]
{
  val genotype: List[T] = l
  protected val problem: Problem = p

  val value: Double;

  override def compare(that: Chromosome): Int = this.value compare that.value
}