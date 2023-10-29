/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.interfaces

import core.dataTypes.InitializedData
import core.dataTypes.Point

interface Initializer {
    fun InitializedData.init(): Point
}
