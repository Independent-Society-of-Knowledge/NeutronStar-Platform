/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.interfaces

import core.dataTypes.ComputationalVariable
import core.dataTypes.InitializedData
import core.dataTypes.Point
import core.functionTypes.Limit

interface Initializer<T, U> {
    val initializedData: InitializedData<T>
    val computationalVariable: ComputationalVariable<U>
    fun initialPoint(): Point


    fun computationalVariables(): ComputationalVariable<U>
}
