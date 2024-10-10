package legacy.utils.computational

fun Double.format(scale: Int) = "%.${scale}f".format(this)
