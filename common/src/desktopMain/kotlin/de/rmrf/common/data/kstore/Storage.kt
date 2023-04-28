package de.rmrf.common.data.kstore

import de.rmrf.common.di.appStorage
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf

actual val store: KStore<PreviousConnections> by lazy {
    storeOf("$appStorage/saved.json", emptySet())
}
