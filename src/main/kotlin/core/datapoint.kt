package core

data class Point(
    val pressure: Double,
    val density: Double,
    val mass: Double,
    val radius: Double,
)

data class InitialValues(
    val density: Double,
    val radius: Double,
    val limit: Double,
    val stepSize: Double
)
