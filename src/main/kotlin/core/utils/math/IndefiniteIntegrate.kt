package core.utils.math

import core.functionTypes.IndefiniteIntegral
import core.functionTypes.Integrand

inline fun indefiniteIntegrate(
    precision: Double = 10e-8,
    maxSecondDerivativeValue: Double = 1.0,
    crossinline integrand: Integrand,
): IndefiniteIntegral = { l, h ->
    definiteIntegrate(l to h, precision, maxSecondDerivativeValue, integrand)
}
