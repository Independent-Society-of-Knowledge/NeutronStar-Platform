/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.interfaces

import core.dataTypes.Point
import core.dataTypes.Script
import core.functionTypes.OneToOneFunction

interface Initializer {

    /**
     * From the initializedData, makes a point ready for method interface:
     * @return Point
     */
    fun initialize(script: Script): Pair<Point, OneToOneFunction>
}
