package core.utils.computational

fun giveFrom(
    end: Double, start: Double = 0.0, step: Double = 0.01
): Sequence<Double>{
    return generateSequence(start) { it + step }
        .takeWhile { it <= end }
}

fun main() {
    giveFrom(100.00).forEach { println(it) }
}
