package de.rmrf.common.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.rmrf.common.data.ChannelName
import de.rmrf.common.data.DaemonStatus
import de.rmrf.common.di.Mixer
import de.rmrf.common.io.GoXLRCommand
import de.rmrf.common.navigation.ScreenRoutes
import de.rmrf.common.ui.mixer.fader.FaderBox


@Composable
fun MainScreen(
    daemonStatus: DaemonStatus?,
    mixer: Mixer?,
    sendCommand: (GoXLRCommand) -> Unit,
    updateScreen: (ScreenRoutes) -> Unit
) {


    //println(state.mixer)
    daemonStatus?.let {
        mixer?.let {
            Text("${mixer.mixer.ifEmpty { "Nothing" }} selected")

            daemonStatus.getMixerStatus(mixer.mixer)?.let { it1 ->
                FaderBox(
                    name = "Mixer",
                    faders = listOf(
                        ChannelName.Mic,
                        ChannelName.Music,
                        ChannelName.Chat,
                        ChannelName.System,
                    ),
                    mixerStatus = it1,
                    sendCommand = sendCommand
                )
            }
        }

    }


}
