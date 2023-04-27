package de.rmrf.common.di

import de.rmrf.common.io.WebsocketHandler
import org.koin.dsl.module

val module = module {
    single { WebsocketHandler }
}
