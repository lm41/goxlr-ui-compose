package de.rmrf.common

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import de.rmrf.common.data.kstore.PreviousConnection
import de.rmrf.common.data.kstore.PreviousConnections
import de.rmrf.common.data.kstore.store
import de.rmrf.common.di.module
import de.rmrf.common.di.rememberMainAppState
import de.rmrf.common.ui.SelectGoXLR
import de.rmrf.common.util.digets
import de.rmrf.common.util.int
import kotlinx.coroutines.runBlocking
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

/*TODO:
 * Add DB for remembering devices
 * Split ViewHolder for all modules
 * Rename ViewHolder
 * Transfer all commands for sending to daemon
 * Add a navigation system
 * Add the screens.
 * Finish the fader screen
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewHolder() {
    var ip by remember { mutableStateOf("") }
    var ipError by remember { mutableStateOf(false) }
    var port by remember { mutableStateOf("") }
    var portError by remember { mutableStateOf(false) }
    val isSubmitted = remember { mutableStateOf(false) }
    val horizontalScrollState = rememberScrollState()

    if (isSubmitted.value) {
        val state = rememberMainAppState(koinInject(parameters = { parametersOf(ip, port.toInt()) }))
        if (state.mixer.isEmpty()) {
            SelectGoXLR(state)
        } else {
            Text("${state.mixer} selected")
        }
    } else {
        Row(
            Modifier.horizontalScroll(horizontalScrollState)
        ) {

            Column {
                TextField(
                    value = ip,
                    onValueChange = { newText -> ip = newText },
                    label = { Text("Enter ip address:") },
                    isError = ipError
                )
                TextField(
                    value = port,
                    onValueChange = { newText -> port = newText.digets },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    label = { Text("Enter port:") },
                    isError = portError
                )
                Button(
                    onClick = {
                        if (null != port.int && ip != "") {
                            isSubmitted.value = true
                            runBlocking {
                                store.update {
                                    val set = it?.toMutableSet()
                                    set?.add(PreviousConnection(ip, port.int!!))
                                    set?.toSet()
                                }
                            }
                        }
                        portError = null == port.int
                        ipError = "" == ip
                    }
                ) {
                    Text(text = "Submit ip and port!")
                }
            }
            var previousConnections: PreviousConnections? = null

            runBlocking {
                previousConnections = store.get()
            }

            previousConnections?.let {
                LazyColumn {
                    this.items(it.toList()) {
                        Button(
                            onClick = {
                                ip = it.ip
                                port = it.port.toString()
                            }
                        ) {
                            Text("${it.ip}:${it.port}")
                        }
                    }
                }
            }

        }
    }
}
