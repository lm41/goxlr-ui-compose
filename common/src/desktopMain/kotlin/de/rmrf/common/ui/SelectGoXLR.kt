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
import de.rmrf.common.di.rememberMainAppState

@Composable
actual fun SelectGoXLR() {
    val state = rememberMainAppState()
    state.webSocketHandler.state?.let {
        Column {
            Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth()){
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

