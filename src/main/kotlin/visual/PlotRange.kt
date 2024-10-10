package visual

import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.export.ggsave  // For saving to file
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.themes.themeMinimal

// import org.jetbrains.letsPlot.jfx.export.show // Uncomment for JavaFX

fun Sequence<Double>.plot(name: String = "Plot", xLabel: String = "x", yLabel: String = "y", evaluator: (Double) -> Double) {
    val xValues = this.toList()
    val yValues = xValues.map { evaluator(it) }

    // Create a data map for plotting
    val data = mapOf<String, Any>(
        xLabel to xValues,
        yLabel to yValues
    )

    // Create the plot
    val p = letsPlot(data)
    p + ggsize(1028, 720) + ggtitle(name) + themeMinimal()
    val plt = p + geomLine { x = xLabel; y = yLabel }


    // Example: Save to file or show depending on environment
    ggsave(plt, "plot_${name}.svg")  // Saves the plot to a PNG file

    // Uncomment the following line for JavaFX display if using JavaFX
    // p.show()
}



//fun main() {
//    giveFrom(10.0, 1.0, step = 0.001).asSequence().forEach { println(it) }
//    giveFrom(10.0, 1.0, step = 0.000001).asSequence().plot {
//        sin(it)
//    }
//}