package legacy.utils.math

fun Sequence<Double>.numericIntegrate(stepSize: Double): Double{
    return this.map {
        it * stepSize
    }.sum()
}

//fun main() {
//    println(functionSample(0.0, 2 * PI, 0.000001) {
//        cos(this).pow(2.0)
//    }.asSequence().asSequence().numericIntegrate(0.000001))
//}
