package utils

infix fun Double.isInBetween(other: List<Double>): Boolean {
    return !(this isBiggestIn other && this isSmallestIn other)
}