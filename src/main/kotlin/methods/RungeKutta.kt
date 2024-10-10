package methods

//import
//fun rungeKutta(stepSize: Double, initialValues: Point, equationOfMotion: (Point) -> Point): Sequence<Point> =
//    sequence<Point> {
//
//        var point = initialValues.copy()
//        val stepSize2 = stepSize / 2.0
//
//        yield(point.copy())
//
//        while (true) {
//
//            val k1 = equationOfMotion(point)
//            val k2 = equationOfMotion(point + k1 * stepSize2)
//            val k3 = equationOfMotion(point + k2 * stepSize2)
//            val k4 = equationOfMotion(point + k3 * stepSize)
//            point += (k1 +  k2 * 2.0 +  k3 * 2.0 + k4) * (stepSize / 6.0)
//            point.radius += stepSize
//
//            yield(point.copy())
//
//        }
//    }


