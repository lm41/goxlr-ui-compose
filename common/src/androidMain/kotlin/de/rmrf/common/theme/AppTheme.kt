package de.rmrf.common.theme

import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun AppTheme(useDarkTheme: Boolean, content: @Composable () -> Unit) {
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        dynamicColor && useDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
                dynamicColor && !useDarkTheme -> dynamicLightColorScheme(LocalContext.current)
        useDarkTheme -> darkColorScheme()
                else -> lightColorScheme()
    }
    MaterialTheme(
        colorScheme = colors
    ) {
        content()
    }

}
