package de.rmrf.common.ui

import androidx.compose.runtime.Composable
import de.rmrf.common.data.DaemonStatus
import de.rmrf.common.di.Mixer
import de.rmrf.common.navigation.ScreenRoutes

@Composable
fun SelectionScreen(
    daemonStatus: DaemonStatus?,
    selectSerialNumber: (String) -> Unit,
    mixer: Mixer?,
    updateScreen: (ScreenRoutes) -> Unit
) {

    if (mixer == null) {
        SelectGoXLR(daemonStatus, selectSerialNumber, updateScreen)
    } else {
        updateScreen(ScreenRoutes.MainScreen)
    }
}
