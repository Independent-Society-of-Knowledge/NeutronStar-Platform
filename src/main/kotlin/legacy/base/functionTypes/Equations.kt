package legacy.base.functionTypes

typealias Equation<T>  = T.()-> Double
typealias Equations<T> = List<Equation<T>>
