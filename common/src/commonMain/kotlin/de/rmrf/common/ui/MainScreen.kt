package de.rmrf.common.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.rmrf.common.di.rememberMainAppState


@Composable
fun MainScreen() {
    val state = rememberMainAppState()

    println(state.mixer)

    Text("${state.mixer.ifEmpty { "Nothing" }} selected")

}
