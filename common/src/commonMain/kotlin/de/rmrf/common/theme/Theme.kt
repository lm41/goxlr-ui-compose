package de.rmrf.common.theme


import androidx.compose.runtime.Composable

@Composable
expect fun AppTheme(useDarkTheme: Boolean, content: @Composable () -> Unit)
