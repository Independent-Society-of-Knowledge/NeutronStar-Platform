/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.dataTypes

import java.io.File

data class Script(
    val path: String
){
    fun open(): File {
        return File(path)
    }
}
