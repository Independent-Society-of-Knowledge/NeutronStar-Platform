package utils

infix fun Double.isBiggestIn(other: List<Double>): Boolean {
    return other.filter { it > this }.count() < 1
}

