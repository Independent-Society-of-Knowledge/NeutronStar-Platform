package core
import core.TovSolver
import core.base.dataTypes.Point
import core.base.dataTypes.pointOf
import core.base.functionTypes.Equation
import core.base.methods.rungeKutta
import core.utils.computational.giveFrom
import core.utils.computational.saveToCsv
import java.io.File
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.pow
import kotlin.streams.asStream


fun main() {
    File("fermi").deleteRecursively()
    File("fermi").mkdirs()
    val idGen = AtomicLong()
    val result = mutableListOf<Triple<Double, Double, Double>>(Triple(0.0, 0.0, 0.0))
    val listOfGammas = listOf<Double>(2.0, 4.0 / 3.0, 5.0 / 3.0, 5.0 / 4.0, 1.0, 11.0 / 6.0, 3.0 / 2.0)
    listOfGammas.forEach { gamma ->
        File(gamma.toString()).mkdirs()
        giveFrom(10.0, 0.0001, step = 0.0001)
            .asStream()
            .parallel()
            .forEach { initialDensity ->
                val id = idGen.incrementAndGet()
                val neutronTest = TovSolver(
                    gamma.toString(), // Saving Path
                    gamma.toString(), // Plotting Path
                    { this[1] > 1e-10 && !this[1].isNaN() && !this[1].isInfinite() }, // Limits
                    100, // How much data is good enough
                    id, // Solver ID for Savings
                    initialValues = pointOf(0.001, initialDensity), // Initial Values
                    equationOfState = Pair(
                        first = {
                            val kappa = 0.05
                            kappa * it.pow(gamma)       // Density -> Pressure
                                },
                        second = {
                            val kappa = 0.05
                            (it / kappa).pow(1 / gamma)// Pressure -> Density
                        })
                )
                neutronTest.eval()
            }
    }
}