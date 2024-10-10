import java.io.File
import kotlin.math.*

// Constants (Define these with appropriate values)
const val MeVfm_3Tokm_2 = 1.0  // Conversion factor (Example value)
const val km_2ToMeVfm_3 = 1.0  // Conversion factor (Example value)
const val kmToModot = 1.0  // Conversion factor (Example value)
const val ModotTokm = 1.0  // Conversion factor (Example value)

// Data class for the EoS table
data class EoS(val density: Double, val pressure: Double, val energyDensity: Double)


// Read EoS from a CSV file and return as a list of EoS objects
fun readEoS(filename: String): List<EoS> {
    val eosList = mutableListOf<EoS>()
    val inputPath = File("./src/main/resources/$filename")
    inputPath.readLines().drop(1).forEach { line ->
        val columns = line.trim().split(",").map { it.toDouble() }  // Split by commas
        if (columns.size >= 3) {  // Ensure there are at least 3 columns
            eosList.add(EoS(columns[0], columns[1], columns[2]))
        }
    }
    return eosList
}

// Join low and high-density EoS tables
fun joinEoS(lowDensityEos: List<EoS>, highDensityEos: List<EoS>): List<EoS> {
    val index = minOf(
        lowDensityEos.indexOfFirst { it.density > highDensityEos[0].density },
        lowDensityEos.indexOfFirst { it.pressure > highDensityEos[0].pressure },
        lowDensityEos.indexOfFirst { it.energyDensity > highDensityEos[0].energyDensity }
    )
    return lowDensityEos.subList(0, index) + highDensityEos
}

// Interpolation functions
fun density(P: Double, peos: List<Double>, neos: List<Double>): Double {
    if (P < 0) return 0.0
    val i = peos.indexOfFirst { it > P }
    return if (i == 0) {
        neos[0]
    } else {
        val last = neos.size - 1
        if (i <= last) {
            neos[i - 1] * exp(ln(P / peos[i - 1]) * ln(neos[i - 1] / neos[i]) / ln(peos[i - 1] / peos[i]))
        } else {
            neos[last]
        }
    }
}

fun energyDensity(P: Double, peos: List<Double>, eeos: List<Double>): Double {
    if (P < 0) return 0.0
    val i = peos.indexOfFirst { it > P }
    return if (i == 0) {
        eeos[0]
    } else {
        val last = eeos.size - 1
        if (i <= last) {
            eeos[i - 1] * exp(ln(P / peos[i - 1]) * ln(eeos[i - 1] / eeos[i]) / ln(peos[i - 1] / peos[i]))
        } else {
            eeos[last]
        }
    }
}

fun pressure(n: Double, neos: List<Double>, peos: List<Double>): Double {
    if (n < 0) return 0.0
    val i = neos.indexOfFirst { it > n }
    return if (i == 0) {
        peos[0]
    } else {
        val last = peos.size - 1
        if (i <= last) {
            peos[i - 1] * exp(ln(n / neos[i - 1]) * ln(peos[i - 1] / peos[i]) / ln(neos[i - 1] / neos[i]))
        } else {
            peos[last]
        }
    }
}

// TOV equation derivatives
fun tovDerivatives(u: DoubleArray, r: Double, peos: List<Double>, neos: List<Double>, eeos: List<Double>): DoubleArray {
    val p = u[0]
    val m = u[1]

    if (p <= 0) return doubleArrayOf(0.0, 0.0)  // Avoid negative pressure

    val e = energyDensity(p, peos, eeos)
    val n = density(p, peos, neos)

    val dPdr = -((e + p) * (m + 4.0 * PI * r * r * r * p)) / (r * (r - 2.0 * m))
    val dMdr = 4.0 * PI * r * r * n

    return doubleArrayOf(dPdr, dMdr)
}

// Fourth-order Runge-Kutta step
fun rk4Step(u: DoubleArray, r: Double, dr: Double, peos: List<Double>, neos: List<Double>, eeos: List<Double>): DoubleArray {
    val k1 = tovDerivatives(u, r, peos, neos, eeos)
    val k2 = tovDerivatives(u.mapIndexed { i, ui -> ui + 0.5 * dr * k1[i] }.toDoubleArray(), r + 0.5 * dr, peos, neos, eeos)
    val k3 = tovDerivatives(u.mapIndexed { i, ui -> ui + 0.5 * dr * k2[i] }.toDoubleArray(), r + 0.5 * dr, peos, neos, eeos)
    val k4 = tovDerivatives(u.mapIndexed { i, ui -> ui + dr * k3[i] }.toDoubleArray(), r + dr, peos, neos, eeos)

    return u.mapIndexed { i, ui -> ui + (dr / 6.0) * (k1[i] + 2 * k2[i] + 2 * k3[i] + k4[i]) }.toDoubleArray()
}

// Integrate TOV equations
fun integrateTOV(peos: List<Double>, neos: List<Double>, eeos: List<Double>, pCentral: Double, dr: Double): Pair<Double, Double> {
    var u = doubleArrayOf(pCentral, 0.0)  // Initial conditions: [pressure, mass]
    var r = dr  // Start integration at a small radius

    while (u[0] > 0) {
        u = rk4Step(u, r, dr, peos, neos, eeos)
        r += dr
    }

    val radius = r - dr  // Subtract last step size
    val mass = u[1]

    return Pair(radius * kmToModot, mass * kmToModot)  // Convert to solar mass units
}

// Main function
fun main() {
    // Read EoS files
    val lowDensityEos = readEoS("eos.csv")
    val highDensityEos = readEoS("eos.csv")
    val combinedEos =
                readEoS("eos.csv")
//        joinEoS(lowDensityEos, highDensityEos)

    // Convert to separate lists for interpolation
    val neos = combinedEos.map { it.density }
    val peos = combinedEos.map { it.pressure * MeVfm_3Tokm_2 }
    val eeos = combinedEos.map { it.energyDensity * MeVfm_3Tokm_2 }

    // Integrate TOV for a given central pressure
    val pCentral = 1.0  // Example central pressure
    val dr = 0.1  // Step size for integration
    val (radius, mass) = integrateTOV(peos, neos, eeos, pCentral, dr)

    println("Neutron star radius: $radius km")
    println("Neutron star mass: $mass solar masses")
}
