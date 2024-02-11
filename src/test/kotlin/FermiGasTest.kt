import core.TovSolver
import core.base.dataTypes.Point
import core.base.dataTypes.pointOf
import core.base.functionTypes.Limit
import core.utils.computational.giveFrom
import core.utils.physics.relativisticFermiGas
import core.utils.physics.relativisticFermiGasInverse
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicLong
import kotlin.streams.asStream

class FermiGasTest {

    @Test
    fun `Fermi Gas Equation of State Testing`() {
        val testID: AtomicLong  = AtomicLong()
        val initials: Point =
            pointOf(
                1e-7, //radius
                relativisticFermiGas(1e-6), // pressure
                1e-7,  // mass
                1e-6, // density
                )


        val limit: Limit<Point> = {
            this[1] > 1e-5
        }
        val evaluation = TovSolver(
            "./",
            "./",
            limit,
            1e3.toInt(),
            testID.incrementAndGet(),
            0.01,
            initialValues = initials,
            equationOfState = Pair(relativisticFermiGas, relativisticFermiGasInverse)
            ).eval()
    }
}

fun main() {
    val testID: AtomicLong  = AtomicLong()
    giveFrom(100.0, step =  0.01)
        .asStream()
        .parallel()
        .forEach {
        val initials: Point =
            pointOf(
                1e-10 , it
            )
        val id = testID.incrementAndGet()
        println("$id : with density --> $it")
        val limit: Limit<Point> = {
            this[1] > 1e-5
        }
        val evaluation = TovSolver(
            "./fermi",
            "./fermi",
            limit,
            1e3.toInt(),
            id,
            0.01,
            initialValues = initials,
            equationOfState = Pair(relativisticFermiGas, relativisticFermiGasInverse)
        ).eval()

    }
}