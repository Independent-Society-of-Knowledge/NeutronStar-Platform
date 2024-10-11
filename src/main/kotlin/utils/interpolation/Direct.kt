package utils.interpolation

// Linear interpolation
fun linearInterpolation(xList: List<Double>, yList: List<Double>): (Double) -> Double? {
    return { xVal ->
        if (xVal < xList.first() || xVal > xList.last()) {
            if (xVal < xList.first()) yList.first()
            else yList.last()
        }

        val index = xList.indexOfFirst { it > xVal }
//        println(index)
        if (index < 0 )  0.0
        if (index >= xList.size - 1) yList.max()

        val x0 = xList[index]
        val x1 = xList[index + 1]
        val y0 = yList[index]
        val y1 = yList[index + 1]

        y0 + (y1 - y0) * (xVal - x0) / (x1 - x0)
    }
}