/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.dataTypes

import java.time.Instant


/**
 * Logs and computation reports needed to be saved.
 */
data class ComputationalReport(
    val log: String,
    val time: Instant,
    val error: Boolean
)
