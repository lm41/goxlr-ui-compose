package de.rmrf.common.util

import de.rmrf.common.data.ChannelName
import de.rmrf.common.data.ChannelName.*

val ChannelName.string
    get() = when(this){
        Mic -> "MIC"
        LineIn -> "LINE IN"
        Console -> "CONSOLE"
        System -> "SYSTEM"
        Game -> "GAME"
        Chat -> "VOICE CHAT"
        Sample -> "SAMPLE"
        Music -> "MUSIC"
        Headphones -> "HEADPHONES"
        MicMonitor -> "MIC MONITOR"
        LineOut -> "LINE OUT"
    }
