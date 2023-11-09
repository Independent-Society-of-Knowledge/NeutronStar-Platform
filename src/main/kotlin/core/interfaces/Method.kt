/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.interfaces

import core.dataTypes.ComputationalVariable
import core.functionTypes.Limit
import java.awt.Point


interface Method<T>: Iterator<T> {
    val firstPoint: T
    val computationalVariable: ComputationalVariable<T>
    /**
     * The function returns the last computed point.
     * @return Point
     */
    fun last(): T

    /**
     *  The function transforms corrections into a point type to
     *  be added to the last computed point.
     *
     *  @return Point
     */
    fun correctionsToPoint(corrections: Array<Double>): T

    /**
     * This function checks the default and user defined limitations for the algorithm to run
     * such as time, pressure limit etc
     * @return Boolean
     */
    override fun hasNext(): Boolean

    /**
     * The function is the main method algorithm running each time that is being called.
     * @return Point
     */
    override fun next(): T
}
