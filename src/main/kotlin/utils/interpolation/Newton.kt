package utils.interpolation

// Newton interpolation
fun newtonInterpolation(xList: List<Double>, yList: List<Double>): (Double) -> Double? {
    val dividedDifferences = Array(yList.size) { DoubleArray(yList.size) }
    for (i in yList.indices) {
        dividedDifferences[i][0] = yList[i]
    }

    // Compute divided differences
    for (j in 1 until yList.size) {
        for (i in 0 until yList.size - j) {
            dividedDifferences[i][j] =
                (dividedDifferences[i + 1][j - 1] - dividedDifferences[i][j - 1]) / (xList[i + j] - xList[i])
        }
    }

    return { xVal ->
        var result = dividedDifferences[0][0]
        var productTerm = 1.0

        for (i in 1 until xList.size) {
            productTerm *= (xVal - xList[i - 1])
            result += dividedDifferences[0][i] * productTerm
        }

        result
    }
}