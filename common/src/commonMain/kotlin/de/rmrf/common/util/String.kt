package de.rmrf.common.util

val String.digets: String
    get() = this.filter { it.isDigit() }

val String.int: Int?
    get() = this.toIntOrNull()
