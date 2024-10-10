package eos

import utils.computational.giveFrom
import utils.interpolation.InterpolationMethod
import utils.interpolation.lagrangeInterpolation
import utils.interpolation.linearInterpolation
import utils.interpolation.newtonInterpolation
import utils.interpolation.splineInterpolation
import utils.isInBetween
import visual.plot
import java.io.File
import kotlin.streams.asSequence
import kotlin.streams.asStream

class Tabulated(csvFilePath: String, interpolationMethod: InterpolationMethod = InterpolationMethod.LINEAR) : EOSType {
    val pressureList: List<Double>
    val densityList: List<Double>
    val numerDensityList: List<Double>?
    val chemicalPotentialList: List<Double>?

    val maxOfPressure: Double
    val minOfPressure: Double
    val maxOfDensity: Double
    val minOfDensity: Double
    val maxOfNumerDensity: Double?
    val minOfNumerDensity: Double?
    val maxOfChemicalPotential: Double?
    val minOfChemicalPotential: Double?

    private val rhoInterpolation: (Double) -> Double?
    private val nInterpolation: ((Double) -> Double?)?
    private val muInterpolation: ((Double) -> Double?)?

    init {
        val tableData = readCsvFile(csvFilePath)

        pressureList = tableData.first
        densityList = tableData.second["rho"] ?: throw IllegalArgumentException("Missing rho data")
        numerDensityList = tableData.second["n"]
        chemicalPotentialList = tableData.second["mu"]

        maxOfPressure = pressureList.max()
        minOfPressure = pressureList.min()
        maxOfDensity = densityList.max()
        minOfDensity = densityList.min()

        maxOfNumerDensity = numerDensityList?.max()
        minOfNumerDensity = numerDensityList?.min()
        maxOfChemicalPotential = chemicalPotentialList?.max()
        minOfChemicalPotential = densityList?.min()

        // Initialize the interpolation method for rho
        rhoInterpolation = selectInterpolationMethod(pressureList, densityList, interpolationMethod)

        // Initialize the interpolation method for n and mu if present
        nInterpolation = numerDensityList?.let { selectInterpolationMethod(pressureList, it, interpolationMethod) }
        muInterpolation =
            chemicalPotentialList?.let { selectInterpolationMethod(pressureList, it, interpolationMethod) }
    }

    private fun selectInterpolationMethod(
        xList: List<Double>,
        yList: List<Double>,
        interpolationMethod: InterpolationMethod,
    ): (Double) -> Double? {
        return when (interpolationMethod) {
            InterpolationMethod.LINEAR -> linearInterpolation(xList, yList)
            InterpolationMethod.NEWTON -> newtonInterpolation(xList, yList)
            InterpolationMethod.LAGRANGE -> lagrangeInterpolation(xList, yList)
            InterpolationMethod.SPLINE -> splineInterpolation(xList, yList)
        }
    }

    // Function to read the CSV file and parse it into lists of data
    private fun readCsvFile(filePath: String): Pair<List<Double>, Map<String, List<Double>>> {
        val pressureList = mutableListOf<Double>()
        val rhoList = mutableListOf<Double>()
        val nList = mutableListOf<Double>()
        val muList = mutableListOf<Double>()
        val file = File(filePath)
        file.forEachLine { line ->
            val values = line.split(",").map { it.toDoubleOrNull() }
            if (values.size >= 2 && values[0] != null && values[1] != null) {
                pressureList.add(values[0]!!)
                rhoList.add(values[1]!!)
                if (values.size > 2) {
                    nList.add(values[2] ?: Double.NaN)
                }
                if (values.size > 3) {
                    muList.add(values[3] ?: Double.NaN)
                }
            }
        }

        val dataMap = mutableMapOf("rho" to rhoList)
        if (nList.isNotEmpty()) dataMap["n"] = nList
        if (muList.isNotEmpty()) dataMap["mu"] = muList

        return Pair(pressureList, dataMap)
    }

    // Interpolation functions that return the interpolated values
    fun interpolateRho(p: Double): Double? = rhoInterpolation(p)
    fun interpolateN(p: Double): Double? = nInterpolation?.invoke(p)
    fun interpolateMu(p: Double): Double? = muInterpolation?.invoke(p)

    fun checkInRangeOfPressures(parameter: Double): Boolean {
        return parameter isInBetween pressureList
    }

}

//fun main() {
//    val eos = Tabulated("./src/main/resources/eos.csv", interpolationMethod = InterpolationMethod.SPLINE)
//    giveFrom(eos.maxOfPressure, eos.minOfPressure, step= 0.001)
//        .asSequence()
//        .plot {
//        eos.interpolateRho(it)!!
//    }
//}
