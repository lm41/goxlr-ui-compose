package de.rmrf.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import de.rmrf.common.data.ChannelName
import de.rmrf.common.data.Data
import de.rmrf.common.di.module
import de.rmrf.common.di.rememberMainAppState
import de.rmrf.common.io.WebsocketHandler
import de.rmrf.common.ui.SelectGoXLR
import de.rmrf.common.util.mapToFloat
import de.rmrf.common.util.toPercentageString
import org.koin.compose.koinInject
import org.koin.core.context.startKoin

@Composable
fun App() {

    startKoin {
        modules(module)
    }
    SelectGoXLR()

}
