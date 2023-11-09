package core.utils.math

import core.dataTypes.Point
import core.functionTypes.Differential
import kotlin.math.pow

val massDifferential: Differential<Point> = {
    this.radius.pow(2.0)*this.density
}
