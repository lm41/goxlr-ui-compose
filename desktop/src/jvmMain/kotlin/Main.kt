import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.rmrf.common.App
import de.rmrf.common.di.appStorage
import de.rmrf.common.di.module
import de.rmrf.common.theme.AppTheme
import net.harawata.appdirs.AppDirsFactory
import org.koin.core.context.startKoin


fun main() = application {

    startKoin {
        modules(module)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "GoXLR UI"
    ) {
        appStorage = AppDirsFactory.getInstance().getUserDataDir(
            "goxlr-compose-ui", "1.0.0", "de.rmrf"
        )
        AppTheme(isSystemInDarkTheme()){
            App()
        }
    }
}
