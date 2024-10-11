package utils.computational

import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themes.themeMinimal

//fun Sequence<core.Point>.savePicture(name: String, path: String, takeNumber: Int) {
//    val points = this.take(takeNumber).toList()
//    val plt = letsPlot(
//        mapOf(
//            "radius" to points.map { it.radius },
//            "pressure" to points.map { it.pressure },
//            "mass" to points.map { it.mass }
//        )
//    ) + ggsize(1028, 720) + ggtitle(name) + themeMinimal()
//    val pltPressure = plt + geomLine { x = "radius"; y = "pressure" }
//    val pltMass = plt + geomLine { x = "radius"; y = "mass" }
//
//    ggsave(pltMass, "${name}-mass.png", path = path )
//    ggsave(pltPressure, "${name}-pressure.png", path = path)
//}
