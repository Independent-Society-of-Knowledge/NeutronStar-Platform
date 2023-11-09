/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.dataTypes

import core.functionTypes.Limit
import java.time.Instant


/**
 * Logs and computation reports needed to be saved.
 */
data class ComputationalVariable<T>(
    var log: String,
    val time: Instant,
    var error: Boolean,
    val stepSize: Double,
    val stepNumber: Int,
    val limit: Limit<T>

)
