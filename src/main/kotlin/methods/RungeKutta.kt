package methods

import Point
import visual.plotPairSequence
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

fun rungeKutta(stepSize: Double, initialValues: Point, equationOfMotion: (Point) -> Point): Sequence<Point> =
    sequence<Point> {

        var point = initialValues.copy()
        val stepSize2 = stepSize / 2.0

        yield(point.copy())

        while (true) {
            val radius = point.radius
            val k1 = equationOfMotion(point)
            val k2 = equationOfMotion(point + k1 * stepSize2)
            val k3 = equationOfMotion(point + k2 * stepSize2)
            val k4 = equationOfMotion(point + k3 * stepSize)
            point += (k1 + k2 * 2.0 + k3 * 2.0 + k4) * (stepSize / 6.0)
            point.radius = radius + stepSize

            yield(point.copy())

        }
    }

//fun main() {
//    rungeKutta(0.1, Point(
//        pressure = 1.0,
//        mass = 0.0,
//        radius = 0.01,
//        density = 0.0,
//        baryonDensity = 0.0
//    ), {
//        Point(
//            pressure =  -sin(it.radius) ,
//            mass = cos(it.radius),
//            radius = 1.0 ,
//            density = 0.0,
//            baryonDensity = 0.0
//        )
//    }).take(100)
//        .map { it.radius to it.pressure }
//        .plotPairSequence()
//}
