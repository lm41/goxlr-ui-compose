import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.rmrf.common.App
import de.rmrf.common.di.appStorage
import net.harawata.appdirs.AppDirsFactory


fun main() = application {


Window(
        onCloseRequest = ::exitApplication,
        title = "GoXLR UI"
    ) {
    appStorage = AppDirsFactory.getInstance().getUserDataDir(
        "de.rmrf.goxlr-compose-ui", "1.0.0", "lm41"
    )
    App()
}
}
