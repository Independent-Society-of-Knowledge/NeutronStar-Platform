package core
import kotlin.math.pow

object Default : Tov {
    override fun equationOfState(density: Double): Double {
        val kappa: Double = 1.0
        val gamma: Double = 5.0 / 3.0
        return density.pow(gamma) * kappa
    }

    override fun pressureEq(point: Point): Double {
        return (-1.0 / point.radius.pow(2)) * (point.density + equationOfState(point.density)) *
                (point.mass + point.radius.pow(3) * equationOfState(point.density)) / (1.0 - (2 * point.mass / point.radius))
    }

    override fun massEq(point: Point): Double {
        return (point.radius.pow(2)) * point.density
    }

}
object DefaultInverse : Tov {
    override fun equationOfState(pressure: Double): Double {
        val kappa: Double = 1.0
        val gamma: Double = 3.0 / 5.0
        return (pressure / kappa).pow(gamma)
    }

    override fun pressureEq(point: Point): Double {
        return (-1.0 / point.radius.pow(2)) * (equationOfState(point.pressure) + point.pressure) *
                (point.mass + point.radius.pow(3) * point.pressure) / (1.0 - (2 * point.mass / point.radius))
    }

    override fun massEq(point: Point): Double {
        return (point.radius.pow(2)) * equationOfState(point.pressure)
    }

}


