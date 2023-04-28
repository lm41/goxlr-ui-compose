package de.rmrf.common.ui

import androidx.compose.runtime.Composable
import de.rmrf.common.di.Mixer
import de.rmrf.common.di.rememberMainAppState
import de.rmrf.common.navigation.Navigator
import de.rmrf.common.navigation.ScreenRoutes
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun SelectionScreen() {
    val mixer: Mixer = koinInject { parametersOf("") }
    val state = rememberMainAppState()
    if (state.mixer.isEmpty()) {
        SelectGoXLR(state)
    } else {
        Navigator.navigate(ScreenRoutes.MainScreen.route)
    }
}
