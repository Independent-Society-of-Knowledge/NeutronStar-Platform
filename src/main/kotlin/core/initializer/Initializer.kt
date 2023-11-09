/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.initializer

import core.dataTypes.ComputationalVariable
import core.dataTypes.InitializedData
import core.dataTypes.Point
import core.functionTypes.Limit
import core.interfaces.Initializer
import core.utils.math.definiteIntegrate
import java.time.Instant
import kotlin.math.PI
import kotlin.math.pow

class Initializer(
    override val initializedData: InitializedData<Double>,
    override val computationalVariable: ComputationalVariable<Point>
) : Initializer<Double, Point> {
    override fun initialPoint(): Point {
        val radius: Double = initializedData.initialVariable
        val pressure: Double
        val density: Double
        if(initializedData.isInverse){
            pressure = initializedData.primaryValue
            density = initializedData.equationOfState(initializedData.primaryValue)
        }
        else{
            density = initializedData.primaryValue
            pressure = initializedData.equationOfState(initializedData.primaryValue)
        }
        val mass: Double = definiteIntegrate(Pair(0.0, radius)){
            4 * PI * it.pow(2.0) * density
        }
        return Point(
            radius = radius,
            pressure = pressure,
            density = density,
            mass = mass
        )

    }


    override fun computationalVariables(): ComputationalVariable<Point> {
        return computationalVariable
    }
}
