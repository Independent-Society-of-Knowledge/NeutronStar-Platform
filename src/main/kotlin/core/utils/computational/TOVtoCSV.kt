package core.utils.computational

import core.base.dataTypes.Point

fun Sequence<Point>.saveToCSV(id: String) {
    this.saveToCsv("fermi/$id")
}
