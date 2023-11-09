import core.dataTypes.ComputationalReport
import core.dataTypes.InitializedData
import org.junit.jupiter.api.Test
import java.time.Instant

class FirstOrderTests {

    @Test
    fun `Testing First Order Differential Equation Solving`() {
        val initial = InitializedData<Pair<Double, Double>>(
            4.0,
            0.01,
            0.1,
            false,
        ) {
            this.second <= 10000
        }
        val report: ComputationalReport = ComputationalReport(
            "Starting The Method",
            Instant.now(),
            false
        )



        val solver = OneDimensionalRungeKuttaSolver(
            initial,
            report,
        ) {
            val x = it.first
            val y = it.second
            -x*y
        }

        solver.asSequence()
            .take(100)
            .toList()
    }

    @Test
    fun `Testing Solver For a Harmonic Oscillator`(){
        val initialData: InitializedData<Pair<Double, Double>> = InitializedData(
            1.0,
            0.1,
            0.0,
            false
        ){
            true
        }

        val report: ComputationalReport = ComputationalReport(
            "Initial Log",
            Instant.now(),
            false
        )

    }
}
