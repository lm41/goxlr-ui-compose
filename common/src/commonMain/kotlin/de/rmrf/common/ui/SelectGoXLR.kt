package de.rmrf.common.ui

import androidx.compose.runtime.Composable
import de.rmrf.common.data.DaemonStatus
import de.rmrf.common.navigation.ScreenRoutes

@Composable
expect fun SelectGoXLR(
    daemonStatus: DaemonStatus?,
    updateSerialNumber: (String) -> Unit,
    updateScreen: (ScreenRoutes) -> Unit
)
