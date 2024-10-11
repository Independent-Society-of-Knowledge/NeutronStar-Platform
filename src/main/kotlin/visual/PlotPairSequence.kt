package visual

import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themes.themeMinimal

fun Sequence<Pair<Double, Double>>.plotPairSequence(name: String = "Plot", xLabel: String = "x", yLabel: String = "y", path: String = "./polytropic/plots") {
    val xList = this.map { it.first }
    val yList = this.map { it.second }
    // Create a data map for plotting
    val data = mapOf<String, Any>(
        xLabel to xList,
        yLabel to yList
    )

    // Create the plot
    val p = letsPlot(data)
    p + ggsize(1920, 1080) + ggtitle(name) + ggtitle(title = name) + themeMinimal()
    val plt = p + geomLine { x = xLabel; y = yLabel }


    // Example: Save to file or show depending on environment
    ggsave(plt, "plot_${name}.svg", path = path)  // Saves the plot to a PNG file

    // Uncomment the following line for JavaFX display if using JavaFX
    // p.show()

}

