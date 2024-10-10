package utils.physics

import kotlin.math.pow

const val c: Double = 2.99792458e8 // The Speed of Light in Nat
const val G: Double = 6.67430e-11  // The Gravitational Constant in Nat
const val h: Double = 6.62607015e-34 // Plank Constant in Nat
const val neutronMass: Double = 1.67492749804e-27 // Mass of Neutron kg
const val electronMass: Double = 9.1093837015e-31 // mass of Electron kg
const val kB: Double = 1.380649e-23 // Boltzmann Constant
enum class Quantities {
    Length,
    Time,
    Mass,
    Velocity,
    AngularVelocity,
    Acceleration,
    Energy,
    EnergyDensity,
    AngularMomentum,
    Force,
    Power,
    Pressure,
    Density,
    ElectricCharge,
    ElectricField,
    MagneticField
}

infix fun Double.toGeoUnit(quantities: Quantities): Double{
    when(quantities) {
        Quantities.Length -> return 1.0  * this
        Quantities.Time -> return c * this
        Quantities.Mass -> return G/c.pow(2) * this
        Quantities.Velocity -> return 1/ c * this
        Quantities.AngularVelocity -> return 1/ c * this
        Quantities.Acceleration -> return 1/c.pow(2) * this
        Quantities.Energy -> return G / c.pow(4) * this
        Quantities.EnergyDensity -> return G / c.pow(4) * this
        Quantities.AngularMomentum -> return G / c.pow(3) * this
        Quantities.Force -> return G / c.pow(4) * this
        Quantities.Power -> return G / c.pow(5) * this
        Quantities.Pressure -> return G / c.pow(4) * this
        Quantities.Density -> return G / c.pow(2) * this
        Quantities.ElectricCharge -> return 1.0 * this
        Quantities.ElectricField -> return 1.0 * this
        Quantities.MagneticField -> return 1.0 * this
    }
}
