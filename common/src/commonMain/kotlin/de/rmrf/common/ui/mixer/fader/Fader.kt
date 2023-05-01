package de.rmrf.common.ui.mixer.fader


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.rmrf.common.data.ChannelName
import de.rmrf.common.data.MixerStatus
import de.rmrf.common.io.GoXLRCommand
import de.rmrf.common.util.mapToFloat
import de.rmrf.common.util.mapToUByte
import de.rmrf.common.util.string
import de.rmrf.common.util.toPercentageString

@Composable
fun Fader(
    channelName: ChannelName,
    mixerStatus: MixerStatus,
    sendCommand: (GoXLRCommand) -> Unit
) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.background(Color.DarkGray)
        ) {
            mixerStatus.levels.volumes.let {
                var value by mutableStateOf(it[channelName]?.mapToFloat() ?: 1F)
                Text(
                    channelName.string,
                    color = Color.White
                )
                Slider(
                    modifier = Modifier.rotate(270F).size(120.dp),
                    value = value,
                    onValueChange = { updatedNum: Float ->
                        value = updatedNum
                        sendCommand(GoXLRCommand.SetVolume(channelName, updatedNum.mapToUByte()))
                    },
                    valueRange = 0F..1F
                )
                //Text("Insert fader here!") //TODO: Use this.levels.volumes[channelName]
                Text(
                    value.toPercentageString(),
                    color = Color.White
                )
            }
        }
    }
}
