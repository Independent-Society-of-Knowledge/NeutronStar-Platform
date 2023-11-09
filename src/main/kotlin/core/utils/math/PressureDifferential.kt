package core.utils.math

import core.functionTypes.Differential
import core.dataTypes.Point
import kotlin.math.pow

val pressureDifferential: Differential<Point> = {
    (this.density + this.pressure)*
            (this.mass + this.radius.pow(3.0)*this.pressure)/
            (this.radius.pow(2.0) - 2*this.mass*this.radius)
}
