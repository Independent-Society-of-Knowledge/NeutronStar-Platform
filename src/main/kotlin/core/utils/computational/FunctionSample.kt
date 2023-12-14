package core.utils.computational

fun functionSample(
    start: Double,
    end: Double,
    step: Double = 0.001,
    func: Double.() -> Double
): Sequence<Double> {
    return generateSequence(start) { it + step }
        .takeWhile { it <= end }
        .map { it.func() }
}
