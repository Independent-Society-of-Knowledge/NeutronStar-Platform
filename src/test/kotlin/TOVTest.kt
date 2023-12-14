import core.TovSolver
import core.base.dataTypes.Point
import core.base.dataTypes.pointOf
import core.base.functionTypes.Equation
import core.base.methods.rungeKutta
import core.utils.computational.format
import core.utils.computational.giveFrom
import core.utils.computational.saveToCsv
import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themes.themeMinimal
import org.junit.jupiter.api.Test
import java.io.File
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.pow
import kotlin.streams.asStream

class TOVTest {
    private fun equationOfState(pressure: Double): Double {
        val kappa: Double = 2.4216
        val gamma: Double = 5.0 / 4.0

        return (pressure * kappa).pow(gamma)
    }

    val pressureDiff: Equation<Point> = {
        val radius = this[0]
        val pressure = this[1]
        val mass = this[2]
        -((equationOfState(pressure) + pressure) * (mass + radius.pow(3.0) * pressure)) / (radius.pow(2.0) - 2 * mass * radius)
    }

    val massDiff: Equation<Point> = {
        val radius = this[0]
        val pressure = this[1]
        radius.pow(2.0) * equationOfState(pressure)
    }


    fun generateNeutronStar(initialPoint: Point, stepSize: Double = 0.01): Sequence<Point> =
        rungeKutta(stepSize, initialPoint) {
            pointOf(0.0, pressureDiff(it), massDiff(it))
        }

    @Test
    fun `Testing Fermi Gas equation of State`() {


    }
}

fun main() {


    File("fermi").deleteRecursively()
    File("fermi").mkdirs()
    val idGen = AtomicLong()
    giveFrom(1.0, step = 0.01).forEach { gamma ->
        val result = mutableListOf<Triple<Double, Double, Double>>(Triple(0.0, 0.0, 0.0))
        giveFrom(1.0, step = 0.01)
            .asStream()
            .parallel()
            .forEach { initialDensity ->
                val neutronTest = TovSolver(
                    "fermi", // Saving Path
                    "fermi", // Plotting Path
                    { this[0] < 1e10 && this[1] > 1e-5 && !this[1].isNaN() && !this[1].isInfinite() }, // Limits
                    20_000, // How much data is good enough
                    idGen.incrementAndGet(), // Solver ID for Savings
                    initialValues = pointOf(0.00001, initialDensity), // Initial Values
                    equationOfState = Pair(
                        first = {
                            val kappa = 2.8
                            kappa * it.pow(gamma)       // Density -> Pressure
                        },
                        second = {
                            val kappa = 2.8
                            (it / kappa).pow(1 / gamma)// Pressure -> Density
                        })
                )
                neutronTest.eval()
                if (neutronTest.getMax().second) {
                    result.add(neutronTest.getMax().first)
                }

            }
        println("$gamma has finished")
        if (result.size > 20) {
            println(result)
            result.asSequence()
                .saveToCsv(
                    "./max/result${gamma.format(10)}",
                    Triple("maxRadius", "maxPressure", "maxMass")
                )

            val plt = letsPlot(
                mapOf(
                    "maxRadius" to result.map { it.first }.drop(1),
                    "maxPressure" to result.map { it.second }.drop(1),
                    "maxMass" to result.map { it.third }.drop(1)
                )
            ) + ggsize(1028, 720) + themeMinimal()
            val pltPressure = plt + ggtitle("maxRadius vs maxPressure") + geomPoint() {
                x = "maxRadius"
                y = "maxPressure"
            }
            val pltMass = plt + ggtitle("maxRadius vs maxPressure") + geomPoint() {
                x = "maxRadius"
                y = "maxMass"
            }
            ggsave(pltPressure, filename = "maxPre${gamma.format(10)}.png")
            ggsave(pltMass, filename = "maxMas${gamma.format(10)}.png")
        }
    }
}
