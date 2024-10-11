package tov

import core.Point
import kotlin.math.PI
import kotlin.math.pow

fun Point.massDifferential(): Double {
    return 4 * PI * radius.pow(2) * density
}