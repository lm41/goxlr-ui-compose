package de.rmrf.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun AppTheme(useDarkTheme: Boolean, content: @Composable () -> Unit) {
    val colors = if (useDarkTheme) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    MaterialTheme(colorScheme = colors){
        Surface(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

