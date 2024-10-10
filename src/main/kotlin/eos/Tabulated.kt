package eos

import utils.interpolation.InterpolationMethod
import utils.interpolation.lagrangeInterpolation
import utils.interpolation.linearInterpolation
import utils.interpolation.newtonInterpolation
import utils.interpolation.splineInterpolation
import utils.isInBetween
import java.io.File

class Tabulated(csvFilePath: String, interpolationMethod: InterpolationMethod = InterpolationMethod.LINEAR) : EOSType {
    private val xList: List<Double>
    private val rhoList: List<Double>
    private val nList: List<Double>?
    private val muList: List<Double>?

    private val rhoInterpolation: (Double) -> Double?
    private val nInterpolation: ((Double) -> Double?)?
    private val muInterpolation: ((Double) -> Double?)?

    init {
        val tableData = readCsvFile(csvFilePath)

        xList = tableData.first
        rhoList = tableData.second["rho"] ?: throw IllegalArgumentException("Missing rho data")
        nList = tableData.second["n"]
        muList = tableData.second["mu"]

        // Initialize the interpolation method for rho
        rhoInterpolation = selectInterpolationMethod(xList, rhoList, interpolationMethod)

        // Initialize the interpolation method for n and mu if present
        nInterpolation = nList?.let { selectInterpolationMethod(xList, it, interpolationMethod) }
        muInterpolation = muList?.let { selectInterpolationMethod(xList, it, interpolationMethod) }
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
        val xList = mutableListOf<Double>()
        val rhoList = mutableListOf<Double>()
        val nList = mutableListOf<Double>()
        val muList = mutableListOf<Double>()

        val file = File(filePath)
        file.forEachLine { line ->
            val values = line.split(",").map { it.toDoubleOrNull() }
            if (values.size >= 2 && values[0] != null && values[1] != null) {
                xList.add(values[0]!!)
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

        return Pair(xList, dataMap)
    }

    // Interpolation functions that return the interpolated values
    fun interpolateRho(p: Double): Double? = rhoInterpolation(p)
    fun interpolateN(p: Double): Double? = nInterpolation?.invoke(p)
    fun interpolateMu(p: Double): Double? = muInterpolation?.invoke(p)

    fun checkInRangeOfPressures(parameter: Double): Boolean {
        return parameter isInBetween xList
    }

}