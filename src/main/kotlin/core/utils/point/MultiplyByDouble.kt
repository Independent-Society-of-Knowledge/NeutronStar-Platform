package core.utils.point

import core.dataTypes.Point

infix fun Point.multiplyByDouble(other: Double): Point {
    return Point(
        this.pressure * other,
        this.density * other,
        this.mass * other,
        this.radius * other
    )
}
