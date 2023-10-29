/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.interfaces

import core.dataTypes.InitializedData
import core.dataTypes.Point

interface Initializer {

    /**
     * From the initializedData, makes a point ready for method interface:
     * @return Point
     */
    fun InitializedData.init(): Point
}
