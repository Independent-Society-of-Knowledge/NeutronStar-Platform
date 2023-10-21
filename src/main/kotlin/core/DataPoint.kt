package core

data class Point(
    val pressure: Double,
    val density: Double,
    val mass: Double,
    val radius: Double,
)

data class InitialValues(
    val primaryValue: Double,
    val radius: Double,
    val inversed: Boolean
)

data class ComputationalRestrictions(
    val limit: Double,
    val stepSize: Double,
    val inversed: Boolean
)
