package core.utils.computational

import core.dataTypes.ComputationalReport
import core.dataTypes.Point

fun ComputationalReport.logger() {
    val header = if (this.error) "ERR" else "LOG"
    println("$header - ${this.time}: ${this.log}")
}

fun Point.logger() {
    println(
        """
        pressure: ${this.pressure} 
        density:  ${this.density}
        mass: ${this.mass}
        radius: ${this.radius}
    """.trimIndent()
    )
}
