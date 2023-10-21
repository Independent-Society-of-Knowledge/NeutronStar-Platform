package core

interface Tov {
    fun equationOfState(primaryValue: Double): Double
    fun pressureEq(point: Point): Double
    fun massEq(point: Point): Double
}

fun Sequence<Pair<Double, Double>>.cross(){

}
