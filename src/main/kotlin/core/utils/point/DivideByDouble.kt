package core.utils.point

import core.dataTypes.Point

infix fun Point.divideByDouble(other: Double): Point {
    return Point(
        this.pressure / other,
        this.density / other,
        this.mass / other,
        this.radius / other
    )
}
