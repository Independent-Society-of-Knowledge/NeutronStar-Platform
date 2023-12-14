package core.utils.computational

import core.base.dataTypes.Point
import java.io.File

fun Collection<Pair<Number,Number>>.saveToCsv(fileName: String, cols: Pair<String, String>){
    File("$fileName.csv").printWriter().use { out ->
        out.println("${cols.first},${cols.second}")
        this.forEach {
            out.println("${it.first},${it.second}")
        }
    }

}

fun Sequence<Triple<Number, Number, Number>>.saveToCsv(fileName: String, cols: Triple<String, String, String>){
    File("$fileName.csv").printWriter().use { out ->
        out.println("${cols.first},${cols.second},${cols.third}")
        this.forEach {
            out.println("${it.first},${it.second},${it.third}")
        }
    }

}

fun Sequence<Point>.saveToCsv(fileName: String) {
    File("$fileName.csv").printWriter().use { out ->
        out.println("radius,pressure,mass")
        this.forEach {
            out.println("${it[0]},${it[1]},${it[2]}")
        }
    }
}
