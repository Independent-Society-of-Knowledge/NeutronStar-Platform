package core.utils.point

import core.dataTypes.Point

operator fun Point.plusAssign(other: Point) {
    Point(
        this.pressure + other.pressure,
        this.density + other.density,
        this.mass + other.mass,
        this.radius + other.radius
    )
}
