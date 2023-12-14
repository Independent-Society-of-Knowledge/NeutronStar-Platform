package core.utils.computational

import java.io.File
import java.net.URI

fun csvOpen(csvFilePath: URI, columnIndex: Int): Sequence<Double> {
    return File(csvFilePath)
        .useLines { lines ->
            lines.drop(1) // Skip the header if there is one
                .map { it.split(",")[columnIndex].toDouble() }
        }
}
