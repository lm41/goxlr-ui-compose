package de.rmrf.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.rmrf.common.di.module
import de.rmrf.common.di.rememberMainAppState
import de.rmrf.common.ui.SelectGoXLR
import org.koin.core.context.startKoin

@Composable
fun App() {

    startKoin {
        modules(module)
    }
    ViewHolder()
}

@Composable
fun ViewHolder() {
    val state = rememberMainAppState()
    if (state.mixer.isEmpty()) {
        SelectGoXLR(state)
    } else {
        Text("${state.mixer} selected")
    }
}
