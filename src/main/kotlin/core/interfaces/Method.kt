/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.interfaces


interface Method {

    /**
     * The function returns the last computed point.
     * @return Point
     */
    fun last(): Any

    /**
     *  The function transforms corrections into a point type to
     *  be added to the last computed point.
     *
     *  @return Point
     */
    fun correctionsToPoint(corrections: Array<Double>): Any

    /**
     * This function checks the default and user defined limitations for the algorithm to run
     * such as time, pressure limit etc
     * @return Boolean
     */
    fun hasNext(): Boolean

    /**
     * The function is the main method algorithm running each time that is being called.
     * @return Point
     */
    fun next(): Any
}
