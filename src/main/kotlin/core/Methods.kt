package core

abstract class Methods {
    abstract fun rungeKutta2(eqs: Tov, intialPoint: InitialValues): List<Point>
    abstract fun rungeKutta4(eqs: Tov, initialPoint: InitialValues): List<Point>
    abstract fun rungeKutta45(eqs: Tov, initialPoint: InitialValues): List<Point>
}

class Realizations: Methods() {
    override fun rungeKutta2(eqs: Tov, initialPoint: InitialValues): List<Point>{
        TODO()
    }

    override fun rungeKutta4(eqs: Tov, initialPoint: InitialValues): List<Point>{
        TODO()
    }

    override fun rungeKutta45(eqs: Tov, initialPoint: InitialValues): List<Point>{
        TODO()
    }
}

