package de.rmrf.common.ui.faders

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.rmrf.common.data.*
import de.rmrf.common.util.color
import de.rmrf.common.util.mapToFloat
import de.rmrf.common.util.string
import de.rmrf.common.util.toPercentageString
import java.io.File

@Composable
fun Fader(
    channelName: ChannelName,
    state: WebsocketResponse
) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.background(Color.DarkGray)
        ) {
            with((state.data as Data.Status).status.mixers.toList().first().second){
                Text(
                    channelName.string,
                    color = Color.White
                )
                Text("Insert fader here!") //TODO: Use this.levels.volumes[channelName]
                Text(
                    this.levels.volumes[channelName]?.mapToFloat()?.toPercentageString() ?: "NaN",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun FaderBox(
    modifier: Modifier = Modifier,
    name: String,
    faders: List<ChannelName>,
    state: WebsocketResponse
) {
    Box(
        modifier = modifier,

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(name)
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                for(fader in faders){
                    Fader(fader, state)
                }
            }
        }
    }
}

@Preview
@Composable
fun FaderBoxPreview() {
    val mapper = jacksonObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)

    val state: WebsocketResponse = mapper.readValue<WebsocketResponse>(File("/home/lm41/IdeaProjects/goxlr-compose-ui/common/src/commonMain/resources/test.json"))

    Row {
        FaderBox(
            name = "MIXER",
            faders = listOf(
                ChannelName.Mic,
                ChannelName.Chat,
                ChannelName.Music,
                ChannelName.Game,
                ChannelName.Console,
                ChannelName.LineIn,
                ChannelName.LineOut,
                ChannelName.System,
                ChannelName.Sample
            ),
            state = state
        )
        FaderBox(
            name = "HEADPHONES",
            faders = listOf(
                ChannelName.Headphones,
                ChannelName.MicMonitor
            ),
            state = state
        )
    }


}



@Preview
@Composable
fun FaderPreview() {

    val mapper = jacksonObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)

    val state: WebsocketResponse = mapper.readValue<WebsocketResponse>(File("/home/lm41/IdeaProjects/goxlr-compose-ui/common/src/commonMain/resources/test.json"))


    Row {
        Fader(
            channelName = ChannelName.Mic,
            state = state
        )
        Fader(
            channelName = ChannelName.Chat,
            state = state
        )
        Fader(
            channelName = ChannelName.Music,
            state = state
        )
        Fader(
            channelName = ChannelName.Game,
            state = state
        )
        Fader(
            channelName = ChannelName.Console,
            state = state
        )
        Fader(
            channelName = ChannelName.LineIn,
            state = state
        )
        Fader(
            channelName = ChannelName.LineOut,
            state = state
        )
        Fader(
            channelName = ChannelName.System,
            state = state
        )
        Fader(
            channelName = ChannelName.Sample,
            state = state
        )
    }


}
