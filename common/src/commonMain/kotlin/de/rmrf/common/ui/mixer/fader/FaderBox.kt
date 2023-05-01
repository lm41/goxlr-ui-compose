package de.rmrf.common.ui.mixer.fader

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.rmrf.common.data.ChannelName
import de.rmrf.common.data.MixerStatus
import de.rmrf.common.io.GoXLRCommand

@Composable
fun FaderBox(
    modifier: Modifier = Modifier,
    name: String,
    faders: List<ChannelName>,
    mixerStatus: MixerStatus,
    sendCommand: (GoXLRCommand) -> Unit
) {
    val horizontalScrollState = rememberScrollState()
    Box(
        modifier = modifier,

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(name)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.horizontalScroll(horizontalScrollState)
            ) {
                for (fader in faders) {
                    Fader(fader, mixerStatus, sendCommand)
                }
            }
        }
    }
}
