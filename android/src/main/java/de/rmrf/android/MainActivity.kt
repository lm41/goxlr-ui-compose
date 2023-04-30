package de.rmrf.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import de.rmrf.common.App
import de.rmrf.common.di.appStorage
import de.rmrf.common.theme.AppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(useDarkTheme = isSystemInDarkTheme()) {
                appStorage = filesDir.path
                App()
            }
        }
    }
}
