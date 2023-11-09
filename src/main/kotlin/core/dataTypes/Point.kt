/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.dataTypes

/**
 * A Point of calculated Data
 */
data class Point(
    val radius: Double,
    val pressure: Double,
    val density: Double,
    val mass: Double
){

    operator fun Point.div(other: Point): Point {
        return Point(
            this.pressure / other.pressure,
            this.density / other.density,
            this.mass / other.mass,
            this.radius / other.radius
        )
    }
    operator fun Point.plus(other: Point): Point {
        return Point(
            this.pressure + other.pressure,
            this.density + other.density,
            this.mass + other.mass,
            this.radius + other.radius

        )
    }

    operator fun Point.plusAssign(other: Point) {
        Point(
            this.pressure + other.pressure,
            this.density + other.density,
            this.mass + other.mass,
            this.radius + other.radius
        )
    }

    infix fun Point.divideBy(other: Double): Point {
        return Point(
            this.pressure / other,
            this.density / other,
            this.mass / other,
            this.radius / other
        )
    }
    infix fun Point.divideBy(other: Int): Point {
        return Point(
            this.pressure / other,
            this.density / other,
            this.mass / other,
            this.radius / other
        )
    }
    fun Point.isNaN(): Boolean {
        return this.pressure.isNaN() || this.density.isNaN() || this.radius.isNaN() || this.mass.isNaN()
    }

    infix fun Point.multiplyBy(other: Double): Point {
        return Point(
            this.pressure * other,
            this.density * other,
            this.mass * other,
            this.radius * other
        )
    }
    infix fun Point.multiplyBy(other: Int): Point {
        return Point(
            this.pressure * other,
            this.density * other,
            this.mass * other,
            this.radius * other
        )
    }



}
