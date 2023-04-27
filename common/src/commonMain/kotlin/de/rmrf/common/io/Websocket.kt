package de.rmrf.common.io

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.*
import okio.ByteString
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.flipkart.zjsonpatch.CompatibilityFlags
import com.flipkart.zjsonpatch.JsonPatch
import de.rmrf.common.data.*
import okio.ByteString.Companion.toByteString
import okio.internal.commonAsUtf8ToByteArray
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

object WebsocketHandler {

    private val mapper: ObjectMapper = jacksonObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    lateinit var data: JsonNode
    var state by mutableStateOf<Data.Status?>(null)
    private var id = 0L

    private val client: WebSocket

    init {
        println("Initialize ${this::class.java.name}")
        client = OkHttpClient()
            .newWebSocket(
                Request.Builder().url("ws://192.168.178.136:14564/api/websocket").build(),
                SocketListen {
                    handleMessage(it)
                }
            )
        println("Client successfully created")
        getFirstStatus()
    }

    private fun getFirstStatus(){
        client.send("{ \"id\": $id, \"data\": \"GetStatus\" }")
    }

    private fun handleMessage(message: String) {
        println(message)
        try {
            val response: WebsocketResponse = mapper.readValue(message)
            if (response.id != 0L){
                id = response.id
            }
            if (response.data is Data.Status){
                data = mapper.readValue<JsonNode>(message).findPath("data")
                state = response.data
            }
            if (response.data is Data.Patch) {
                JsonPatch.applyInPlace(response.data.patch, data.findPath("Status"))
                state = mapper.treeToValue(data, Data.Status::class.java)
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }




}


/*
//TODO: Rewrite this logic (was is SocketListen will be moved to `handleMessage`)
try {
                state = mapper.readValue<WebsocketResponse>(it)
                if (state!!.data is Data.Status) {
                    data = mapper.readValue<JsonNode>(it)
                }
                if (state!!.data is Data.Patch){
                    JsonPatch.applyInPlace((state!!.data as Data.Patch).patch, data!!.findPath("Status"))
                    state = mapper.treeToValue(data, WebsocketResponse::class.java)
                }

            } catch (e: Exception){
                e.printStackTrace()
            }*/
//this.lastMessage.add(it)

class SocketListen(
    private val onMessage: (String) -> Unit
) : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
        println("onOpen")
        super.onOpen(webSocket, response)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        //println(text)
        onMessage(text)
        super.onMessage(webSocket, text)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        onMessage(webSocket, bytes.toString())
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        println("onClosed: $code - $reason")
        super.onClosed(webSocket, code, reason)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        println("onClosing: $code - $reason")
        super.onClosing(webSocket, code, reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        t.printStackTrace()
        super.onFailure(webSocket, t, response)
    }

}
