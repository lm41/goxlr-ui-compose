package de.rmrf.common.util

import androidx.compose.ui.graphics.Color
import de.rmrf.common.data.TwoColours

val String.color
    get() = Color(this.removePrefix("#").toLong(16) or 0x00000000FF000000)

fun TwoColours.default() = TwoColours("#FFFFFF", "#FFFFFF")
