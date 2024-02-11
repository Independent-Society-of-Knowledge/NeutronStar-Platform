package core.utils.math

import core.base.dataTypes.Point
import core.base.functionTypes.Equation
import kotlin.math.PI
import kotlin.math.pow

val diffMass: Equation<Point> = {
    4 * PI * this[0] * this[1]
}

val diffPressure: Equation<Point> = {
    ((this[1] + this[2]) * (this[3] + 4 * PI * this[0].pow(3) * this[2]))/(this[0]* (this[0] - 2 * this[3]))
}