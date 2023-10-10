package core
import kotlin.math.pow

class Default : Tov {
    override fun Double.equationOfState(): Double {
        val kappa: Double = 1.0
        val gamma: Double = 5.0 / 3.0
        return this.pow(gamma) * kappa
    }

    override fun Point.pressureEq(): Double {
        return (-1.0 / this.radius.pow(2)) * (this.density + this.density.equationOfState()) *
                (this.mass + this.radius.pow(3) * this.density.equationOfState()) / (1.0 - (2 * this.mass / this.radius))
    }

    override fun Point.massEq(): Double {
        return (this.radius.pow(2)) * this.density
    }

}
class DefaultInverse : Tov {
    override fun Double.equationOfState(): Double {
        val kappa: Double = 1.0
        val gamma: Double = 3.0 / 5.0
        return (this / kappa).pow(gamma)
    }

    override fun Point.pressureEq(): Double {
        return (-1.0 / this.radius.pow(2)) * (this.pressure.equationOfState() + this.pressure) *
                (this.mass + this.radius.pow(3) * this.pressure) / (1.0 - (2 * this.mass / this.radius))
    }

    override fun Point.massEq(): Double {
        return (this.radius.pow(2)) * this.pressure.equationOfState()
    }

}


