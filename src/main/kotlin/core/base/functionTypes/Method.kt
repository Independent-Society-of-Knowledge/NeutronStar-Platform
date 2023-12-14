package core.base.functionTypes

typealias Method<T, U> = T.(stepSize: Double, equations: U) -> T
