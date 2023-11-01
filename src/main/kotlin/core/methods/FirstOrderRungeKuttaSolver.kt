package core.methods

import core.dataTypes.ComputationalReport
import core.dataTypes.InitializedData
import core.functionTypes.FirstOrderODE
import core.functionTypes.Limit
import core.interfaces.Method

class FirstOrderRungeKuttaSolver(
    initialPoint: InitializedData,
    computationalReport: ComputationalReport,
    firstOrderODE: FirstOrderODE
) : Method {

    // Methods private variables
    private var lastPoint: Pair<Double, Double>
    private var ode: FirstOrderODE
    private var limit: Limit
    private var stepSize: Double
    private lateinit var corrections: Array<Double>
    private var report: ComputationalReport

    // Initializing
    init {
        lastPoint = Pair(initialPoint.initialRadius, initialPoint.primaryValue)
        limit = initialPoint.limit
        stepSize = initialPoint.stepSize
        report = computationalReport
        ode = firstOrderODE
    }

    override fun last(): Pair<Double, Double> {
        return lastPoint
    }

    override fun correctionsToPoint(corrections: Array<Double>): Pair<Double, Double> {
        return Pair(
            stepSize,
            stepSize * (corrections[0] + 2 * corrections[1] + 2 * corrections[2] + corrections[3]) / 6.0
        )
    }

    override fun hasNext(): Boolean {
        return lastPoint.limit()
    }

    override fun next(): Pair<Double, Double> {
        if (hasNext()) {
            corrections[0] = ode(lastPoint)
            corrections[1] = ode(
                Pair(
                    lastPoint.first + stepSize / 2.0,
                    lastPoint.second + (stepSize * corrections[0] / 2.0)
                )
            )
            corrections[2] = ode(
                Pair(
                    lastPoint.first + stepSize / 2.0,
                    lastPoint.second + (stepSize * corrections[1] / 2.0)
                )
            )
            corrections[3] = ode(
                Pair(
                    lastPoint.first + stepSize,
                    lastPoint.second + (stepSize * corrections[2])
                )
            )
            val changes = correctionsToPoint(corrections)
            lastPoint = Pair(lastPoint.first + changes.first, lastPoint.second + changes.second)
            return lastPoint
        } else {
            TODO("Program Should Halt Automatically")
        }

    }

}
