/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.methods

import core.dataTypes.ComputationalVariable
import core.dataTypes.Point
import core.functionTypes.Limit
import core.interfaces.Method
import core.utils.math.pressureDifferential
import core.utils.math.massDifferential


class RungeKutta4(
    override val firstPoint: Point,
    override val computationalVariable: ComputationalVariable<Point>
) :
    Method<Point> {

    private var lastPoint: Point = firstPoint
    private val stepSize: Double = computationalVariable.stepSize
    private val limit: Limit<Point> = computationalVariable.limit
    private var corrections: Array<Pair<Double, Double>> = Array(4) { Pair(0.0, 0.0) }


    override fun last(): Point {
        return lastPoint
    }

    override fun correctionsToPoint(corrections: Array<Double>): Point {
        TODO("Not yet implemented")
    }

    override fun hasNext(): Boolean {
        return lastPoint.limit()
    }

    override fun next(): Point {
        corrections[0] = Pair(
            lastPoint.pressureDifferential(),
            lastPoint.massDifferential()
        )
         return Point(0.0,0.0,0.0,0.0)
    }
}
