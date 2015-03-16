package hsu.algorithm.statistics

class Statistics {
  private val precision: Double = 1e-6
  private var min: Double = Double.MaxValue
  private var max: Double = Double.MinValue
  private var second_min: Double = Double.MaxValue - 1
  private var second_max: Double = Double.MinValue + 1
  private var sum: Double = 0.0
  private var variance: Double = 0.0
  private var number: Long = 0

  def record(value: Double): Boolean = {
    number += 1
    sum += value
    variance += value * value
    if (min > value + precision) {
      second_min = min
      min = value
    }
    if (max < value - precision) {
      second_max = max
      max = value
    }
    true
  }

  def getNumber() = number
  def getMean() = sum / number
  def getVariance() = {val mean = getMean(); variance / number - mean * mean}
  def getStdev() = Math.sqrt(getVariance())
  def getMin() = min
  def getMax() = max
  def getSecondMax() = second_max
  def getSecondMin() = second_min
}
