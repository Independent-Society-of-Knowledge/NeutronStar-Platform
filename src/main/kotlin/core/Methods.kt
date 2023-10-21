package core

import definiteIntegrate
import divideByDouble
import isNaN
import plus
import kotlin.math.PI
import kotlin.math.pow

abstract class solver(eqs: Tov, initialValues: InitialValues, computationalRestrictions: ComputationalRestrictions) {
    private val eqs: Tov = eqs
    private var point: Point = initializer(initialValues)
    private val limit: Double = computationalRestrictions.limit
    private val stepSize: Double = computationalRestrictions.stepSize
    private val inversed: Boolean = computationalRestrictions.inversed

    abstract fun Pair<Double, Double>.correctionStep(): Point

    abstract fun correction(
        k1: Pair<Double, Double>,
        k2: Pair<Double, Double>,
        k3: Pair<Double, Double>,
        k4: Pair<Double, Double>
    ): Point

    abstract fun initializer(init: InitialValues): Point
    abstract fun hasNext(): Boolean
    abstract fun next(): Point

}

class rungeKutta4Solver(
    eqs: Tov,
    initialValues: InitialValues,
    computationalRestrictions: ComputationalRestrictions
) : solver(eqs, initialValues, computationalRestrictions) {
    private val eqs: Tov = eqs
    private var point: Point = initializer(initialValues)
    private val limit: Double = computationalRestrictions.limit
    private val stepSize: Double = computationalRestrictions.stepSize
    private val inverse: Boolean = computationalRestrictions.inversed

    override fun Pair<Double, Double>.correctionStep(): Point {
        if (inverse) {
            return Point(
                pressure = this.first,
                density = 0.0,
                mass = this.second,
                radius = stepSize
            )
        } else {
            return Point(
                pressure = 0.0,
                density = this.first,
                mass = this.second,
                radius = stepSize
            )
        }
    }

    override fun correction(
        k1: Pair<Double, Double>,
        k2: Pair<Double, Double>,
        k3: Pair<Double, Double>,
        k4: Pair<Double, Double>
    ): Point {
        val correction: Pair<Double, Double> = Pair(
            (k1.first + 2.0 * k2.first + 2.0 * k3.first + k4.first) * (1.0 / 6.0),
            (k1.second + 2.0 * k2.second + 2.0 * k3.second + k4.second) * (1.0 / 6.0)
        )
        return if (inverse) {
            Point(
                pressure = correction.first,
                density = eqs.equationOfState(point.pressure + correction.first),
                mass = correction.second,
                radius = stepSize
            )
        } else {
            Point(
                pressure = eqs.equationOfState(point.density + correction.first),
                density = correction.first,
                mass = correction.second,
                radius = stepSize
            )
        }
    }

    override fun initializer(init: InitialValues): Point {
        if (init.inversed) {
            return Point(
                pressure = init.primaryValue,
                density = eqs.equationOfState(init.primaryValue),
                mass = definiteIntegrate(0.0 to init.radius) {
                    4 * PI * it.pow(2.0) * eqs.equationOfState(init.primaryValue)
                },
                radius = init.radius
            )
        } else {
            return Point(
                pressure = eqs.equationOfState(init.primaryValue),
                density = init.primaryValue,
                mass = definiteIntegrate(0.0 to init.radius) {
                    4 * PI * it.pow(2.0) * init.primaryValue
                },
                radius = init.radius
            )
        }
    }

    override fun hasNext(): Boolean {
        return point.pressure > limit && !point.isNaN()
    }

    override fun next(): Point {
        val k1: Pair<Double, Double> = Pair(
            eqs.pressureEq(point),
            eqs.massEq(point)
        )
        val k2: Pair<Double, Double> = Pair(
            eqs.pressureEq(point + (k1.correctionStep() divideByDouble 2.0)),
            eqs.massEq(point + (k1.correctionStep() divideByDouble 2.0))
        )
        val k3: Pair<Double, Double> = Pair(
            eqs.pressureEq(point + (k2.correctionStep() divideByDouble 2.0)),
            eqs.pressureEq(point + (k2.correctionStep() divideByDouble 2.0))
        )
        val k4: Pair<Double, Double> = Pair(
            eqs.pressureEq(point + k3.correctionStep()),
            eqs.massEq(point + k3.correctionStep())
        )
        point += correction(k1, k2, k3, k4)
        return point
    }
}
fun main() {

}

