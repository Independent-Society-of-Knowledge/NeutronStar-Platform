package core

interface Tov {
    fun equationOfState(density: Double): Double
    fun pressureEq(point: Point): Double
    fun massEq(point: Point): Double
}
