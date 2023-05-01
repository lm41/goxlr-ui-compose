package de.rmrf.common.io

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.flipkart.zjsonpatch.JsonPatch
import de.rmrf.common.data.DaemonStatus
import de.rmrf.common.data.Data
import de.rmrf.common.data.WebsocketResponse
import kotlinx.coroutines.flow.flowOf
import okhttp3.*
import okio.ByteString

class WebsocketHandler(websocketIp: String, websocketPort: Int, val updateDaemonStatus: (DaemonStatus) -> Unit) {

    private val mapper: ObjectMapper = jacksonObjectMapper()
        /*.registerModule(kotlinModule().apply {
            addSerializer(Data::class.java, DataSerializer)
            addDeserializer(Data::class.java, DataDeserializer)
        })*/
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    lateinit var data: JsonNode
    var state by mutableStateOf<Data.Status?>(null)
    val stateFlow = flowOf(state)
    private var id = 0L

    private val client: WebSocket

    init {
        println("Initialize ${this::class.java.name}")
        client = OkHttpClient()
            .newWebSocket(
                Request.Builder().url("ws://$websocketIp:$websocketPort/api/websocket").build(),
                SocketListen {
                    handleMessage(it)
                }
            )
        println("Client successfully created")
        getFirstStatus()
    }

    private fun getFirstStatus() {
        client.send("{ \"id\": $id, \"data\": \"GetStatus\" }")
    }

    fun sendCommand(command: GoXLRCommand, serial: String) {
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true)
        val serializedCommand = mapper.writeValueAsString(command)
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
        val commandTest = "{ \"id\": $id, \"data\": { \"Command\": [\"$serial\", $serializedCommand] } }"

        println(commandTest)
        client.send(commandTest)
    }

    private fun handleMessage(message: String) {
        println(message)
        try {
            val response: WebsocketResponse = mapper.readValue<WebsocketResponse>(message)
            if (response.data is Data.Ok) {
                return
            }
            if (response.id != 0L) {
                id = response.id
            }
            if (response.data is Data.Status) {
                data = mapper.readValue<JsonNode>(message).findPath("data")
                state = response.data
            }
            if (response.data is Data.Patch) {
                JsonPatch.applyInPlace(response.data.patch, data.findPath("Status"))
                state = mapper.treeToValue(data, Data.Status::class.java)
            }
            state?.let {
                updateDaemonStatus(it.status)
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
