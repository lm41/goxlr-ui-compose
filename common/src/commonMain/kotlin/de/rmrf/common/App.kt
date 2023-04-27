package de.rmrf.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.rmrf.common.di.module
import de.rmrf.common.di.rememberMainAppState
import de.rmrf.common.io.WebsocketHandler
import de.rmrf.common.ui.SelectGoXLR
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.compose.getKoin
import org.koin.compose.koinInject
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

@Composable
fun App() {

    startKoin {
        modules(module)
    }
    ViewHolder()
}

@Composable
fun ViewHolder() {
    val ip = "192.168.178.136"
    val port = 14564
    val state = rememberMainAppState(koinInject(parameters = { parametersOf(ip, port) }))
    if (state.mixer.isEmpty()) {
        SelectGoXLR(state)
    } else {
        Text("${state.mixer} selected")
    }
}
