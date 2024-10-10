import eos.EOSType

data class Options(
    // Initial Values.
    val initialRadius: Double,
    val initialPressure: Double?,
    val initialDensity: Double?,
    val initialBaryonDensity: Double?,

    // Other Options.
    val defauldSurfacePrusser: Double,
    val maximumIntegrationRadius: Double,

    // Numerical Evaluation Options.
    val stepSize: Double = 0.05,
    val maxSteps: Int = 1e6.toInt(),
    val eosModel: EOSType
)

