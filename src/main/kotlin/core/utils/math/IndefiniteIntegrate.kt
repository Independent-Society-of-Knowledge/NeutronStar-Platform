package core.utils.math

import core.functionTypes.IndefiniteIntegral
import core.functionTypes.OneToOneFunction

inline fun indefiniteIntegrate(
    precision: Double = 10e-8,
    maxSecondDerivativeValue: Double = 1.0,
    crossinline integrand: OneToOneFunction,
): IndefiniteIntegral = { l, h ->
    definiteIntegrate(l to h, precision, maxSecondDerivativeValue, integrand)
}
