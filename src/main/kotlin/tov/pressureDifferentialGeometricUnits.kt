package tov

import core.Point
import kotlin.math.PI
import kotlin.math.pow

fun Point.pressureDifferentialGeometricUnits(): Double {
    return (-mass * density / radius.pow(2)) * (1 + pressure / density) * (1 + (4 * PI * radius.pow(3) * pressure) / mass) / (1 - 2 * mass / radius)
}