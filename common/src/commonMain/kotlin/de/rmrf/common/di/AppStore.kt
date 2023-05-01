package de.rmrf.common.di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import de.rmrf.common.ViewHolder
import de.rmrf.common.data.DaemonStatus
import de.rmrf.common.io.GoXLRCommand
import de.rmrf.common.io.WebsocketHandler
import de.rmrf.common.navigation.Navigator
import de.rmrf.common.navigation.ScreenRoutes
import de.rmrf.common.ui.MainScreen
import de.rmrf.common.ui.SelectionScreen

internal class AppStore {

    var state: AppState by mutableStateOf(initialState())
        private set

    private fun initialState(): AppState {
        return AppState()
    }


    private inline fun setState(update: AppState.() -> AppState) {
        state = state.update()
    }


    fun setUpSreens(): AppState {
        val navigator = state.navigator
        navigator.addScreen(route = ScreenRoutes.ConnectionScreen.route) {
            ViewHolder(
                onSelect = this@AppStore::createWebsocketHandler,
                updateScreenRoute = this@AppStore::updateScreen
            )
        }
        navigator.addScreen(route = ScreenRoutes.MixerSelectionScreen.route) {
            SelectionScreen(
                daemonStatus = state.daemonStatus,
                mixer = state.mixer,
                selectSerialNumber = this@AppStore::selectMixer,
                updateScreen = this@AppStore::updateScreen
            )
        }
        navigator.addScreen(route = ScreenRoutes.MainScreen.route, true) {
            MainScreen(
                daemonStatus = state.daemonStatus,
                mixer = state.mixer,
                sendCommand = this@AppStore::sendCommand,
                updateScreen = this@AppStore::updateScreen
            )
        }
        navigator.navigate(state.currentScreenRoute.route)
        return state.copy(navigator = navigator)
    }

    fun updateScreen(route: ScreenRoutes) {
        setState {
            copy(currentScreenRoute = route)
        }
        state.navigator.navigate(state.currentScreenRoute.route)
    }

    fun updateDaemonStatus(daemonStatus: DaemonStatus) {
        setState {
            copy(daemonStatus = daemonStatus)
        }
    }

    fun createWebsocketHandler(ip: String, port: Int) {
        setState {
            copy(
                websocketHandler = WebsocketHandler(
                    websocketIp = ip,
                    websocketPort = port,
                    this@AppStore::updateDaemonStatus
                )
            )
        }
    }

    fun sendCommand(command: GoXLRCommand) {
        state.mixer?.let {
            state.websocketHandler?.sendCommand(command, it.mixer)
        }
    }

    fun selectMixer(mixerSerial: String) {
        setState {
            copy(mixer = Mixer(mixerSerial))
        }
    }

    data class AppState(
        val daemonStatus: DaemonStatus? = null,
        val mixer: Mixer? = null,
        val websocketHandler: WebsocketHandler? = null,
        val currentScreenRoute: ScreenRoutes = ScreenRoutes.ConnectionScreen,
        val navigator: Navigator = Navigator
    )


}
