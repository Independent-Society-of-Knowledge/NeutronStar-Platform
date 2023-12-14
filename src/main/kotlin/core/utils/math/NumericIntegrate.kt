package core.utils.math

import core.utils.computational.functionSample
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

fun Collection<Double>.numericIntegrate(stepSize: Double): Double{
    return this.map {
        it * stepSize
    }.sum()
}

fun main() {
    println(functionSample(0.0, 2 * PI, 0.000001) {
        cos(this).pow(2.0)
    }.asSequence().toList().numericIntegrate(0.000001))
}
