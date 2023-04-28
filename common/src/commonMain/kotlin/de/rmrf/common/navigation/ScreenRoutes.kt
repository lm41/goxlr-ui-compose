package de.rmrf.common.navigation

sealed class ScreenRoutes(val route: String) {
    object ConnectionScreen : ScreenRoutes("connection")

    object MixerSelectionScreen : ScreenRoutes("mixerSelect")

    object MainScreen : ScreenRoutes("main")
}
