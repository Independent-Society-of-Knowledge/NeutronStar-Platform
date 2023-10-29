package core.utils.math

import core.functionTypes.Integrand
import kotlin.math.pow

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
