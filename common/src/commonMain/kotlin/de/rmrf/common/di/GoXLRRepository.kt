package de.rmrf.common.di

import de.rmrf.common.data.WebsocketResponse
import de.rmrf.common.util.getLast

interface GoXLRRepository {
    fun getLatestGoXLRState() : WebsocketResponse

    fun addGoXLRState(state: WebsocketResponse)
}

class GoXLRRepositoryImpl : GoXLRRepository {

    private val _goxlr = arrayListOf<WebsocketResponse>()

    override fun getLatestGoXLRState(): WebsocketResponse {
        return _goxlr.getLast()
    }

    override fun addGoXLRState(state: WebsocketResponse) {
        _goxlr.add(state)
    }

}




