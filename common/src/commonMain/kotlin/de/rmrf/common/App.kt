package de.rmrf.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewHolder() {
    var ip by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("") }
    val isSubmitted = remember { mutableStateOf(false) }

    if (isSubmitted.value){
        val state = rememberMainAppState(koinInject(parameters = { parametersOf(ip, port.toInt()) }))
        if (state.mixer.isEmpty()) {
            SelectGoXLR(state)
        } else {
            Text("${state.mixer} selected")
        }
    } else {
        Column {
            TextField(
                value = ip,
                onValueChange = { newText -> ip = newText },
                label = { Text("Enter ip address:") }
            )
            TextField(
                value = port,
                onValueChange = { newText -> port = newText },
                label = { Text("Enter port address:") }
            )
            Button(
                onClick = {
                    isSubmitted.value = true
                }
            ) {
                Text(text = "Submit ip and port!")
            }
        }
    }
}
