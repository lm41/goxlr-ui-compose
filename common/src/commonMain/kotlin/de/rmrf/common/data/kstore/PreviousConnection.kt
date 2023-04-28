package de.rmrf.common.data.kstore

import kotlinx.serialization.Serializable

@Serializable
data class PreviousConnection(
    val ip: String,
    val port: Int
)
