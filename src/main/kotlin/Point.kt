data class Point(
    var pressure: Double,
    var mass: Double,
    var radius: Double,
    var density: Double,
    var baryonDensity: Double,
) {
    operator fun plus(other: Point): Point {
        return Point(
            this.pressure + other.pressure,
            this.mass + other.mass,
            this.radius + other.radius,
            this.density + other.density,
            this.baryonDensity + other.baryonDensity)
    }

    operator fun minus(other: Point): Point {
        return Point(
            this.pressure - other.pressure,
            this.mass - other.mass,
            this.radius - other.radius,
            this.density - other.density,
            this.baryonDensity - other.baryonDensity
        )
    }
    operator fun unaryMinus(): Point {
        return Point(
            -this.pressure,
            -this.mass,
            -this.radius,
            -this.density,
            -this.baryonDensity
        )
    }
    operator fun times(scalar: Double): Point {
        return Point(
            this.pressure * scalar,
            this.mass * scalar,
            this.radius * scalar,
            this.density * scalar,
            this.baryonDensity * scalar
        )
    }
    operator fun div(scalar: Double): Point {
        return Point(
            this.pressure / scalar,
            this.mass / scalar,
            this.radius / scalar,
            this.density / scalar,
            this.baryonDensity / scalar
        )
    }
    operator fun div(scalar: Float): Point {
        return this.div(scalar.toDouble())
    }
}
