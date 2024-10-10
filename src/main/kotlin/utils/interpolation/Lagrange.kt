package utils.interpolation

// Lagrange interpolation
fun lagrangeInterpolation(xList: List<Double>, yList: List<Double>): (Double) -> Double? {
    return { xVal ->
        var result = 0.0

        for (i in xList.indices) {
            var term = yList[i]
            for (j in xList.indices) {
                if (i != j) {
                    term *= (xVal - xList[j]) / (xList[i] - xList[j])
                }
            }
            println(term)
            result += term
        }

        result
    }
}