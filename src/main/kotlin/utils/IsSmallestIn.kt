package utils

infix fun Double.isSmallestIn(other: List<Double>): Boolean {
    return other.filter { it < this }.count() < 1
}