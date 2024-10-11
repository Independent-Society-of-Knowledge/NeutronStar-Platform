package solutions

import core.Point
import eos.Polytropic
import methods.rungeKutta
import tov.massDifferential
import tov.pressureDifferential
import tov.pressureDifferentialGeometricUnits
import utils.computational.giveFrom
import visual.plotPairSequence
import java.io.File
import kotlin.streams.asStream

fun main() {
    val solutionName = "Polytropic"
    File("./$solutionName/plots/max").mkdirs()
    File("./$solutionName/plots/mass").mkdir()
    File("./$solutionName/plots/pressure").mkdir()

    val polytropic = Polytropic(kappa = 10.0, gamma = 2.0)
    val maxRadius: MutableList<Double> = mutableListOf<Double>()
    val maxMass: MutableList<Double> = mutableListOf<Double>()
    val initials: MutableList<Double> = mutableListOf<Double>()
    // Plotting the EOS
//    giveFrom(1.0, 0.0, 0.001).asSequence().plot(name = "$solutionName-${polytropic.gamma}-${polytropic.kappa}") {
//        polytropic.densityByPressure(it)
//    }

    giveFrom(470.446318, 0.01, 0.001)
        .asSequence()
        .asStream()
        .parallel()
        .forEach { initialPressure ->
            println("Initial Pressure: $initialPressure")
            //Initializing the solver
            val solvedValues = rungeKutta(1e-3, Point(
                pressure = initialPressure,
                density = 1e-16,
                mass = 1e-16,
                radius = 1e-8,
                baryonDensity = 0.0
            ), {
                Point(
                    pressure = it.pressureDifferentialGeometricUnits(),
                    mass = it.massDifferential(),
                    density = polytropic.differentialDensityByPressure(it.pressure),
                    baryonDensity = 1.0,
                    radius = it.radius
                )
            }).takeWhile { it.pressure > 1e-3 && it.mass > 0 && it.radius < 5e3 }



            if (solvedValues.count() > 100) {
                println("$initialPressure produced good data!")
                println(solvedValues.last())
                // Plots overall values
                maxMass.add(solvedValues.map { it.mass }
                    .max())
                maxRadius.add(solvedValues.map { it.radius }
                    .last())
                initials.add(initialPressure)
//                 Plots of single stars
                solvedValues.map {
                    it.radius to it.pressure
                }.plotPairSequence("r-P-${initialPressure}", path = "./$solutionName/plots/pressure/")
                solvedValues.map {
                    it.radius to it.mass
                }.plotPairSequence("r-m-${initialPressure}", path = "./$solutionName/plots/mass/")
            }

        }
    maxMass.zip(maxRadius).asSequence().sortedBy { it.first }
        .plotPairSequence("MRMaxes", path = "./$solutionName/plots/max/")
    maxMass.zip(initials).asSequence().sortedBy { it.second }
        .plotPairSequence("MPMaxes", path = "./$solutionName/plots/max/")
    maxRadius.zip(initials).asSequence().sortedBy { it.second }
        .plotPairSequence("RPMaxes", path = "./$solutionName/plots/max/")


}