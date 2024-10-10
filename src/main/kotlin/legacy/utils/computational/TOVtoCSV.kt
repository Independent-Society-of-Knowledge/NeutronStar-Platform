package legacy.utils.computational

import legacy.base.dataTypes.Point

fun Sequence<Point>.saveToCSV(id: String) {
    this.saveToCsv("fermi/$id")
}
