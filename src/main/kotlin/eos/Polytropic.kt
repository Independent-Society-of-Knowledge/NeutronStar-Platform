package eos

import convertMevKm
import convertMevfm3gcm3
import convertMevfm3km2
import convertfm3km3
import e
import electricCharge
import speedOfLight
import utils.computational.giveFrom
import visual.plot
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.sqrt

data class Polytropic(
    val kappa: Double,
    val gamma: Double,
) : EOSType {
    private val densityNucL = (1.66e14 * convertMevfm3km2) / convertMevfm3gcm3
    private val mBL = (1.66e-27 * speedOfLight.toDouble().pow(2) * convertMevKm) / (electricCharge * 1e6)
    private val nNucL = 1e-1 * convertfm3km3

    val computedKappa = this.kappa * densityNucL / nNucL.pow(gamma)

    fun densityByPressure(pressure: Double): Double {
        return mBL * (pressure / computedKappa).pow(1 / gamma) + (1 / (gamma + 1)) * pressure
    }

    fun differentialDensityByPressure(pressure: Double): Double {
        return (1 / (gamma - 1)) + (mBL * (pressure / computedKappa).pow(1 / gamma)) / (pressure * gamma)
    }

    fun numberDensityByPressure(pressure: Double): Double {
        return (pressure / computedKappa).pow(1 / gamma)
    }

    // TODO(What is H?)
    fun logOfHByPressure(pressure: Double): Double {
        return log(
            1 + pressure * (pressure / computedKappa).pow(-1 / gamma) * (gamma / gamma - 1) / mBL,
            e
        )
    }

    fun pressureByLogOfH(logOfH: Double): Double {
        return (((-1 + e.pow(logOfH)) * mBL * (-1 + gamma) * computedKappa.pow(-1 / gamma)) / gamma)
            .pow(gamma / (-1 + gamma))
    }

    // TODO(What does CS Mean)
    fun csByPressure(pressure: Double): Double {
        return sqrt(1 / differentialDensityByPressure(pressure))
    }

}

//fun main (){
//    val poly = Polytropic(
//        kappa = 5.3882,
//        gamma = 5.0
//    )
//
//    giveFrom(0.2, 0.0, 0.001).asSequence().plot {
//        poly.differentialDensityByPressure(it)
//    }
//}