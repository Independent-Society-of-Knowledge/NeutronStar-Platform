package core

import core.dataTypes.Script
import core.interfaces.Initializer
import core.interfaces.Method
import core.interfaces.Solver

class DefaultSolver(
    override val script: Script,
    override val initializer: Initializer,
    override val method: Method
) :
    Solver
