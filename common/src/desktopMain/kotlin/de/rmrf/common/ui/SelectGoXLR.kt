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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.rmrf.common.data.DaemonStatus
import de.rmrf.common.navigation.ScreenRoutes

@Composable
actual fun SelectGoXLR(
    daemonStatus: DaemonStatus?,
    updateSerialNumber: (String) -> Unit,
    updateScreen: (ScreenRoutes) -> Unit
) {

    daemonStatus?.let {
        it.mixers.let { hm ->
            if (hm.size == 1) {
                updateSerialNumber(hm.toList()[0].first)
                updateScreen(ScreenRoutes.MainScreen)
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
                    this.items(it.mixers.toList()) {
                        Button(
                            onClick = {
                                //println("${it.first} clicked")
                                updateSerialNumber(it.first)
                                //println(state.currentState!!.mixers[state.mixer])
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

