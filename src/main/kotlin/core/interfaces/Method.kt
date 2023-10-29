/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.interfaces

import core.dataTypes.Point



interface Method {
    /**
     * This function checks the default and user defined limitations for the algorithm to run
     * such as time, pressure limit etc
     * @return Boolean
     */
    fun hasNext(point: Point): Boolean

    /**
     * The function is the main method algorithm running each time that is being called.
     * @return Point
     */
    fun next(point: Point): Point
}
