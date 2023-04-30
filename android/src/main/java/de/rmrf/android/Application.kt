package de.rmrf.android

import android.app.Application
import de.rmrf.common.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        org.koin.core.context.startKoin {
            modules(module)
            androidContext(this@MainApplication)
            androidLogger()
        }
    }
}
