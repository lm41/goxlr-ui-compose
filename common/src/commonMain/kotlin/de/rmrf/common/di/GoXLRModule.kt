package de.rmrf.common.di

import de.rmrf.common.io.WebsocketHandler
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val module = module {
    singleOf(::WebsocketHandler)
    singleOf(::Mixer)
}


class Mixer(var mixer: String = "")
