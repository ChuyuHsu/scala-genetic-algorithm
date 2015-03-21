package hsu.algorithm.evolutionary

abstract class Chromosome[T](l: List[T], p: Problem[T])
  extends Ordered[Chromosome[T]]
{
  val genotype: List[T] = l
  protected val problem: Problem[T] = p

  val value: Double;

  override def compare(that: Chromosome[T]): Int
}