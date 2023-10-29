/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.interfaces

import core.functionTypes.EquationOfState
import core.dataTypes.InitializedData

interface Script {

    // The Path of the script.
    val path: String

    /**
     * ---- Initialization of the Script Written By User ----
     * This function is the starting point of the algorithm, It should assert any priori errors
     * that could be in the written script by the user.
     * @return The object that can be passed to the solve interface as input.
     */
    fun Script.open(): Pair<InitializedData, EquationOfState>
}
