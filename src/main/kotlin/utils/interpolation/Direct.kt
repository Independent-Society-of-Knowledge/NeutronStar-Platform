package utils.interpolation

// Linear interpolation
fun linearInterpolation(xList: List<Double>, yList: List<Double>): (Double) -> Double? {
    return { xVal ->
        if (xVal < xList.first() || xVal > xList.last()) null

        val index = xList.indexOfFirst { it > xVal } - 1
        if (index < 0 || index >= xList.size - 1)  null

        val x0 = xList[index]
        val x1 = xList[index + 1]
        val y0 = yList[index]
        val y1 = yList[index + 1]

        y0 + (y1 - y0) * (xVal - x0) / (x1 - x0)
    }
}