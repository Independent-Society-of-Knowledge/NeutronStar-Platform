package core.interfaces

import core.dataTypes.Point
import core.dataTypes.Script
import core.functionTypes.OneToOneFunction

interface Solver {
    val script: Script
    val initializer: Initializer
    val method: Method

    // A point and the Equation of State
    val startingValues: Pair<Point, OneToOneFunction>
        get() = initializer.initialize(script)


    // TODO: Implement The Method

}
