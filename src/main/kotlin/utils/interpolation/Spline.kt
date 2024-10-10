package utils.interpolation

import kotlin.math.pow


// Spline interpolation (Cubic Spline Interpolation)
fun splineInterpolation(xList: List<Double>, yList: List<Double>): (Double) -> Double? {
    val n = xList.size - 1
    val h = DoubleArray(n) { xList[it + 1] - xList[it] }
    val alpha = DoubleArray(n)

    for (i in 1 until n) {
        alpha[i] = (3 / h[i] * (yList[i + 1] - yList[i])) - (3 / h[i - 1] * (yList[i] - yList[i - 1]))
    }

    val l = DoubleArray(n + 1)
    val mu = DoubleArray(n + 1)
    val z = DoubleArray(n + 1)
    l[0] = 1.0
    mu[0] = 0.0
    z[0] = 0.0

    for (i in 1 until n) {
        l[i] = 2 * (xList[i + 1] - xList[i - 1]) - h[i - 1] * mu[i - 1]
        mu[i] = h[i] / l[i]
        z[i] = (alpha[i] - h[i - 1] * z[i - 1]) / l[i]
    }

    l[n] = 1.0
    z[n] = 0.0

    val c = DoubleArray(n + 1)
    val b = DoubleArray(n)
    val d = DoubleArray(n)

    for (j in n - 1 downTo 0) {
        c[j] = z[j] - mu[j] * c[j + 1]
        b[j] = (yList[j + 1] - yList[j]) / h[j] - h[j] * (c[j + 1] + 2 * c[j]) / 3
        d[j] = (c[j + 1] - c[j]) / (3 * h[j])
    }

    return { xVal ->
        if (xVal < xList.first() || xVal > xList.last())  null

        val i = xList.indexOfLast { it <= xVal }
        val deltaX = xVal - xList[i]

        yList[i] + b[i] * deltaX + c[i] * deltaX.pow(2) + d[i] * deltaX.pow(3)
    }
}