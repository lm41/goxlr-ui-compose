package de.rmrf.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.rmrf.common.di.MainAppState
import de.rmrf.common.di.Mixer
import de.rmrf.common.di.module
import de.rmrf.common.di.rememberMainAppState
import de.rmrf.common.navigation.Navigator
import de.rmrf.common.navigation.ScreenRoutes
import org.koin.compose.koinInject
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

@Composable
actual fun SelectGoXLR(state: MainAppState) {
    state.webSocketHandler.state?.let {
        it.status.mixers.let { hm ->
            if (hm.size == 1) {
                state.mixer = hm.toList()[0].first
                koinInject<Mixer>(parameters = { parametersOf("") }).mixer = state.mixer
                state.currentState = it.status
                println("${state.mixer} selected")
                Navigator.navigate(ScreenRoutes.MainScreen.route)
            }
        }

        Column {
            Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth()) {
                Text("Please select a device:", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn {
                    this.items(it.status.mixers.toList()) {
                        Button(
                            onClick = {
                                println("${it.first} clicked")
                                state.mixer = it.first
                                state.currentState = state.webSocketHandler.state!!.status
                                println(state.currentState!!.mixers[state.mixer])
                            },
                        ) {
                            Text(it.first)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectGoXLRPreview() {
    startKoin {
        modules(module)
    }
    SelectGoXLR(rememberMainAppState(koinInject(parameters = { parametersOf("192.168.178.136", 14564) })))
}
