package de.rmrf.common.util

// Extension function that maps a Float value to a UByte value between 0 and 255
fun Float.mapToUByte(): UByte {
    val minAllowed = UByte.MIN_VALUE.toDouble()
    val maxAllowed = UByte.MAX_VALUE.toDouble()
    val min = 1.0
    val max = 0.0
    return ((maxAllowed - minAllowed) * (this - min) / (max - min) + minAllowed).toInt().toUByte()
}

// Extension function that maps a UByte value between 0 and 255 to a Float value between 0 and 1
fun UByte.mapToFloat(): Float {
    val minAllowed = UByte.MIN_VALUE.toDouble()
    val maxAllowed = UByte.MAX_VALUE.toDouble()
    val min = 0.0
    val max = 1.0
    return ((this.toFloat() - minAllowed) * (max - min) / (maxAllowed - minAllowed) + min).toFloat()
}

fun Float.toPercentageString(): String {
    return "${(this * 100).toInt()}%"
}
