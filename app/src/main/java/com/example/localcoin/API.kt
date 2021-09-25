package com.example.localcoin

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

// NOTE: EAI_NOADDRESS error
// https://stackoverflow.com/questions/6355498/unable-to-resolve-host-url-here-no-address-associated-with-host-name

class API : ViewModel() {
    private val base_url = "portal.coinroutes.com"

    fun launchDataLoad(c: String, q: String) {
        viewModelScope.launch {
            streamPrice(c, q)
        }
    }

    suspend fun streamPrice(currency_pair: String, quantity: String) = withContext(Dispatchers.IO) {
        val RealPriceRequest: JSONObject = JSONObject()
        RealPriceRequest.put("currency_pair", currency_pair)
        RealPriceRequest.put("quantity", quantity)
        val client = HttpClient(CIO) {
            install(WebSockets)
        }

        client.webSocket(
            method = HttpMethod.Get,
            host = base_url,
            port = 80,
            path = "/api/streaming/real_price/?token=6c634e1eacecc4801b000249287fbf923d5c8824"
        ) {
            send(RealPriceRequest.toString())
            while (true) {
                val mesg = incoming.receive() as? Frame.Text
                println(mesg?.readText())
                //val mesgJSON = JSONObject(mesg?.readText().toString())
                //println(mesgJSON.get("product"))
                //println(mesgJSON.get("price"))
            }
        }
        client.close()
    }


}
