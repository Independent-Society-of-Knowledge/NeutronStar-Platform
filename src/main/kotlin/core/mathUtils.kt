import core.Point
import kotlin.math.*

typealias Integrand = (Double) -> Double
typealias IndefiniteIntegral = (lower: Double, upper: Double) -> Double

inline fun definiteIntegrate(
    bound: Pair<Double, Double>,
    precision: Double = 10e-8,
    maxSecondDerivativeValue: Double = 1.0,
    integrand: Integrand,
): Double {

    if (bound.second == bound.first) return 0.0
    val step: Long = run {
        ((1.0 / (12.0 * precision)) * (maxSecondDerivativeValue * (bound.second - bound.first)))
            .pow(0.5)
            .toLong()
    }
    val delta: Double = (bound.second - bound.first) / step

    return (1..step)
        .sumOf {
            val next: Double = it * delta
            val previous: Double = next - delta
            integrand(previous) + integrand(next)
        } * (delta / 2.0) // TODO(WTF??)
}


inline fun indefiniteIntegrate(
    precision: Double = 10e-8,
    maxSecondDerivativeValue: Double = 1.0,
    crossinline integrand: Integrand,
): IndefiniteIntegral = { l, h ->
    definiteIntegrate(l to h, precision, maxSecondDerivativeValue, integrand)
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

fun Point.isNaN(): Boolean {
    return this.pressure.isNaN() || this.density.isNaN() || this.radius.isNaN() || this.mass.isNaN()
}

infix fun Point.divideByDouble(other: Double): Point {
    return Point(
        this.pressure / other,
        this.density / other,
        this.mass / other,
        this.radius / other
    )
}

infix fun Point.divideByInt(other: Int): Point {
    return Point(
        this.pressure / other,
        this.density / other,
        this.mass / other,
        this.radius / other
    )
}

infix fun Point.multiplyByDouble(other: Double): Point {
    return Point(
        this.pressure * other,
        this.density * other,
        this.mass * other,
        this.radius * other
    )
}

infix fun Point.multiplyByInt(other: Int): Point {
    return Point(
        this.pressure * other,
        this.density * other,
        this.mass * other,
        this.radius * other
    )
}
operator fun Point.div(other: Point): Point {
    return Point(
        this.pressure / other.pressure,
        this.density / other.density,
        this.mass / other.mass,
        this.radius / other.radius
    )
}
