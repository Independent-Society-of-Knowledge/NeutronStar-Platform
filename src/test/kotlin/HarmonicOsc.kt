import core.base.dataTypes.Point
import core.base.dataTypes.pointOf
import core.base.methods.rungeKutta
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themes.themeClassic
import org.junit.jupiter.api.Test
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.system.measureNanoTime

class HarmonicOsc {


    var stepSize = 10e-3
    var k = 1.0

    fun generateOscillatorSequence(): Sequence<Point> {


        val rk4HOs = rungeKutta(stepSize, pointOf(0.0, 1.0, 0.0)) {
            // y'' = -ky
            //  Q = [y,y'] , Q' = [y', -ky]
            val time = it[0]
            val position = it[1]
            val velocity = it[2]

            pointOf(0.0, velocity, -k * position)
        }

        return rk4HOs
    }

    @Test
    fun `test harmonic oscillator dynamics - rk4`() {

        val timeTaken = (0..100).map {
            measureNanoTime {
                val point = generateOscillatorSequence().drop(1_000_000).first().copy().apply {
                    // set time to zero
                    set(0, 0.0)
                }
            }
        }.average()

//        Assertions.assertEquals(sqrt(point dot point),1.0,0.00001)
        println(timeTaken.toDouble() / 10e9)
    }

    @Test
    fun `test - one mill plus`() {
        val tp = Executors.newFixedThreadPool(7)
        println(
            (0..1000).map {
                measureNanoTime {
                    val x = (0 until 7).map {
                        tp.submit(Callable {
                            val random = Random()
                            (0..1_000_000 / 7).map { random.nextDouble() }.sum()
                        })
                    }.sumOf {
                        it.get()
                    }
                    println(x)
                }
            }.average() / 1_000_000
        )
    }

}


// to view chart
//fun main() {
//
//    val sequence = HarmonicOsc().generateOscillatorSequence()
//    val timeToCalc = measureNanoTime {
//     sequence.take(1_000_000).toList()
//    }
//
//    println(timeToCalc/1e9)
//
//    var plt = letsPlot(
//        mapOf(
//            "t" to data.map { it[0] },
//            "position" to data.map { it[1] },
//            "velocity" to data.map { it[2] },
//        )
//    )
//
//    plt += geomPoint {
//        x = "velocity"
//        y = "position"
//    }

//
//    plt += ggsize(720, 720)
//    plt += themeClassic()
//    plt += ggtitle("Harmonic OSC")
//    plt.show()

//}
