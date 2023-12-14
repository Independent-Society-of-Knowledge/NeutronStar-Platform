package core.base.dataTypes

data class Point(
    private val data: DoubleArray
) {

    operator fun get(index: Int) = data[index]
    operator fun set(index: Int, value: Double) {
        data[index] = value
    }


    private inline fun throwIfMismatch(other: Point) {
        assert(other.dimension == this.dimension) {
            throw IllegalArgumentException("The dimension of two points must be equal. left: ${this.dimension} right: ${other.dimension}")
        }
    }

    val dimension
        get() = data.size

    operator fun plus(other: Point): Point {
        throwIfMismatch(other)
        return Point(
            DoubleArray(dimension) {
                this.data[it] + other.data[it]
            }
        )
    }


    operator fun minus(other: Point): Point {
        throwIfMismatch(other)
        return Point(
            DoubleArray(dimension) {
                this.data[it] - other.data[it]
            }
        )
    }

    operator fun times(n: Number): Point {
        val nAsDouble = n.toDouble()
        return Point(DoubleArray(dimension) { this.data[it] * nAsDouble })
    }

    operator fun div(n: Number): Point = this.times(1.0 / n.toDouble())

    infix fun dot(other: Point): Double {
        throwIfMismatch(other)
        var acc = 0.0
        for (i in 0..<dimension) {
            acc += data[i] * other.data[i]
        }
        return acc
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        return data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }

    override fun toString(): String = data.contentToString()

    fun copy(): Point = Point(data.clone())
}


operator fun Number.times(other: Point) = other.times(this)

fun pointOf(vararg elements: Double) = Point(elements)
