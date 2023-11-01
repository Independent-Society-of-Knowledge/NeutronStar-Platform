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
data class InitializedData(
    val primaryValue: Double,
    val stepSize: Double,
    val limit: Limit,
    val initialRadius: Double,
    val isInverse: Boolean
)
