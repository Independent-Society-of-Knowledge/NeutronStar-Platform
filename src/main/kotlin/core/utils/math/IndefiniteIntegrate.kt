package core.utils.math

import core.dataTypes.IndefiniteIntegral
import core.dataTypes.Integrand

inline fun indefiniteIntegrate(
    precision: Double = 10e-8,
    maxSecondDerivativeValue: Double = 1.0,
    crossinline integrand: Integrand,
): IndefiniteIntegral = { l, h ->
    definiteIntegrate(l to h, precision, maxSecondDerivativeValue, integrand)
}
