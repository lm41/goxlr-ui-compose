package de.rmrf.common.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.rmrf.common.di.MainAppState
import de.rmrf.common.di.rememberMainAppState

@Composable
actual fun SelectGoXLR(state: MainAppState) {
    val state = rememberMainAppState()
    state.webSocketHandler.state?.let {
        println("test")
        LazyColumn {
            this.items(it.status.mixers.toList()) {
                Button(
                    onClick = {
                        println("$it clicked")

                    },
                ) {
                    Text(it.first)
                }
            }
        }
    }
    println("Test")
}
