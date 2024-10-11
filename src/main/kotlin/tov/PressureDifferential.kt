package tov

import core.Point
import core.gravitationalConstant
import core.speedOfLight
import kotlin.math.PI
import kotlin.math.pow

fun Point.pressureDifferential(): Double {
    val firstTerm = -(gravitationalConstant * mass * density) / radius.pow(2)
    val secondTerm = (1 + pressure / density * speedOfLight.pow(2))
    val thirdTerm = (1 + (4 * PI * radius.pow(3) * pressure) / (mass * speedOfLight.pow(2)))
    val fourthTerm = (1 - 2 * (gravitationalConstant * mass) / (radius * speedOfLight.pow(2))).pow(-1)
    return firstTerm * secondTerm * thirdTerm * fourthTerm
}

