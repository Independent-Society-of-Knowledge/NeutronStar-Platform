package legacy.utils.physics

import kotlin.math.PI
import kotlin.math.pow


// Non-Relativistic Equation of State of a fermi gas.
val nonRelKappa = ((h toGeoUnit Quantities.AngularMomentum).pow(2)/ 2* PI)/(15 * PI.pow(2)
        * (electronMass toGeoUnit Quantities.Mass))* (3 * PI.pow(2) / (neutronMass toGeoUnit Quantities.Mass))
val nonRelativisticFermiGas: (Double) -> Double = {density ->
    nonRelKappa * (density).pow(5.0 / 3.0)
}
val nonRelativisticFermiGasInverse: (Double) -> Double = {pressure ->
    (pressure / nonRelKappa).pow(3.0 / 5.0)
}


// Relativistic Equation of State of a fermi gas.
val relKappa: Double = 0.05 //((h toGeoUnit Quantities.AngularMomentum)/ 2* PI)/(12 * PI.pow(2))* (3 * PI.pow(2) / (neutronMass toGeoUnit Quantities.Mass))
val relativisticFermiGas: (Double) -> Double = {density ->
    relKappa * (density).pow(4.0 / 3.0)
}
val relativisticFermiGasInverse: (Double) -> Double = {pressure ->
    (pressure / relKappa).pow(3.0 / 4.0)
}