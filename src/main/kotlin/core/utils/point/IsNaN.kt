package core.utils.point

import core.dataTypes.Point

fun Point.isNaN(): Boolean {
    return this.pressure.isNaN() || this.density.isNaN() || this.radius.isNaN() || this.mass.isNaN()
}
