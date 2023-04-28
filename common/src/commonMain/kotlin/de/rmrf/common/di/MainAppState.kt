package de.rmrf.common.di

import androidx.compose.runtime.*
import de.rmrf.common.data.DaemonStatus
import de.rmrf.common.data.MixerStatus
import de.rmrf.common.io.WebsocketHandler
import org.koin.compose.koinInject
import org.koin.java.KoinJavaComponent.get


class MainAppState(
    val webSocketHandler: WebsocketHandler,
    iMixer: Mixer = get(clazz = Mixer::class.java)
) {
    private var pMixer by mutableStateOf(iMixer.mixer)

    private var pCurrentState by mutableStateOf(webSocketHandler.state?.status)
    val mixerStatus: MixerStatus?
        get() = currentState?.mixers?.get(pMixer)

    var mixer: String
        get() = pMixer
        set(value) {
            get<Mixer>(Mixer::class.java).mixer = value
            pMixer = value
        }

    var currentState: DaemonStatus?
        get() = pCurrentState
        set(value) {
            pCurrentState = value
        }
}

@Composable
fun rememberMainAppState(
    webSocketHandler: WebsocketHandler = koinInject()
) = remember {
    MainAppState(webSocketHandler = webSocketHandler)
}
