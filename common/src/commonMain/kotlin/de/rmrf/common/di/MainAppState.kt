package de.rmrf.common.di

import androidx.compose.runtime.*
import de.rmrf.common.data.DaemonStatus
import de.rmrf.common.data.Data
import de.rmrf.common.data.MixerStatus
import de.rmrf.common.data.WebsocketResponse
import de.rmrf.common.io.WebsocketHandler
import org.koin.compose.koinInject


class MainAppState(
    val webSocketHandler: WebsocketHandler
) {
    private var pMixer by mutableStateOf<String?>(null)

    private var pCurrentState by mutableStateOf(webSocketHandler.state?.status)
    val mixerStatus: MixerStatus?
        get() = currentState?.mixers?.get(pMixer)

    var mixer: String
        get() = pMixer.orEmpty()
        set(value) {
            pMixer = value
        }

    var currentState: DaemonStatus?
        get() = pCurrentState
        set(value) {
            pCurrentState = value
        }
}

@Composable
fun rememberMainAppState(webSocketHandler: WebsocketHandler = koinInject()) = remember {
    MainAppState(webSocketHandler = webSocketHandler)
}
