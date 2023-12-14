package core

import core.base.dataTypes.Point
import core.base.dataTypes.pointOf
import core.base.functionTypes.Equation
import core.base.functionTypes.Limit
import core.base.methods.rungeKutta
import core.utils.computational.savePicture
import core.utils.computational.saveToCsv
import core.utils.math.definiteIntegrate
import java.io.File
import kotlin.math.pow

class TovSolver(
    private val savingPath: String,
    private val plottingPath: String,
    private val limit: Limit<Point>,
    val isGood: Int,
    private val solverID: Long,
    private val stepSize: Double = 0.01,
    private val maximumSave: Boolean = true,
    val initialValues: Point,
    val equationOfState: Pair<(Double) -> Double, (Double) -> Double> // First is the real and second one is the inverse.
) {
    private var data: Sequence<Point> = sequenceOf(pointOf(0.0, 0.0, 0.0))
    override fun toString(): String = "id-${solverID}"
    private var initialRadius = 0.0
    private var initialMass = 0.0
    private var initialPressure = 0.0
    private var initialDensity = 0.0
    private var maximums = Triple(0.0,0.0,0.0)
    init {
        assert(initialValues.dimension == 2) {
            throw ExceptionInInitializerError()
        }
         initialRadius = initialValues[0]
         initialDensity = initialValues[1]
         initialMass = definiteIntegrate(0.0 to initialRadius) {
            it.pow(2.0) * initialDensity
        }
         initialPressure = equationOfState.first(initialDensity)
    }
    // Differential Equations
    val pressureDiff: Equation<Point> = {
        val radius = this[0]
        val pressure = this[1]
        val mass = this[2]
        -((equationOfState.second(pressure) + pressure) * (mass + radius.pow(3.0) * pressure)) / (radius.pow(2.0) - 2 * mass * radius)
    }
    val massDiff: Equation<Point> = {
        val radius = this[0]
        val pressure = this[1]
        radius.pow(2.0) * equationOfState.second(pressure)
    }

    // Generator
    private fun solve(initialPressure: Double, initialMass: Double, initialRadius: Double) {
        data = rungeKutta(stepSize, pointOf(initialRadius, initialPressure, initialMass)) {
            pointOf(0.0, pressureDiff(it), massDiff(it))
        }.takeWhile(limit).take(isGood)
    }

    private fun postSolve() {
        if (data.take(isGood).count() == isGood) {
            data.saveToCsv("$savingPath/$solverID")
            data.savePicture(solverID.toString(), "$plottingPath/", 10_000)
            if (maximumSave) {
                maximums =  Triple(
                    data.map { it[0] }.max(),
                    data.map { it[1] }.max(),
                    data.map { it[2] }.max()
                )
            }
        }

    }

    fun eval(){
        solve(initialPressure, initialMass, initialRadius)
        postSolve()
    }

    fun getMax(): Pair<Triple<Double, Double,Double>, Boolean> {
        if (data.take(isGood).count() == isGood)
            return Pair(maximums, true)
        else{
            return Pair(maximums, false)
        }
    }
}
