package core.utils.point

import core.dataTypes.Point

infix fun Point.divideByInt(other: Int): Point {
    return Point(
        this.pressure / other,
        this.density / other,
        this.mass / other,
        this.radius / other
    )
}
