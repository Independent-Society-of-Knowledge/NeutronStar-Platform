package utils.interpolation

fun main() {
    val xList = listOf(0.0, 1.0, 2.0, 3.0, 4.0)
    val yList = listOf(0.0, 2.0, 1.0, 4.0, 3.0)

    val linearInterp = linearInterpolation(xList, yList)
    val newtonInterp = newtonInterpolation(xList, yList)
    val lagrangeInterp = lagrangeInterpolation(xList, yList)
    val splineInterp = splineInterpolation(xList, yList)

    val xVal = 2.5
    println("Linear Interpolation at x = $xVal: ${linearInterp(xVal)}")
    println("Newton Interpolation at x = $xVal: ${newtonInterp(xVal)}")
    println("Lagrange Interpolation at x = $xVal: ${lagrangeInterp(xVal)}")
    println("Spline Interpolation at x = $xVal: ${splineInterp(xVal)}")
}
