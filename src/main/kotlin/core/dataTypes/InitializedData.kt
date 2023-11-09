/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.dataTypes

import core.functionTypes.Limit


/**
 * Initialized data before starting the computation.
 */
data class InitializedData<A>(
    val primaryValue: Double,
    val initialVariable: Double,
    val stepSize: Double,
    val isInverse: Boolean,
    val equationOfState: (A)-> Double
)

