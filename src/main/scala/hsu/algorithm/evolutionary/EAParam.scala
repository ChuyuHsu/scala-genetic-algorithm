package hsu.algorithm.evolutionary

case class EAParam(
                    numOfIterations: Int,
                    numOfIndividuals: Int,
                    numOfProblemLength: Int,
                    selectionPressure: Int,
                    crossoverRate: Double,
                    mutationRate: Double
                    )
