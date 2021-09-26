package com.example.localcoin

import android.view.View
import android.widget.TextView
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

    fun launchDataLoad(c: String, q: String, index: Int, v: View) : String {
        var price : String = ""
        viewModelScope.launch {
            price = streamPrice(c, q, index, v)
        }
        return price
    }

    suspend fun streamPrice(currency_pair: String, quantity: String, index: Int, v: View) : String = withContext(Dispatchers.IO) {
        var price = ""
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

                val mesg = incoming.receive() as? Frame.Text
                println(mesg?.readText())
                price = JSONObject(mesg?.readText().toString()).get("price").toString()
                v.findViewById<TextView>(R.id.price1)
                println(price)

                //val mesgJSON = JSONObject(mesg?.readText().toString())
                //println(mesgJSON.get("product"))
                //println(mesgJSON.get("price"))

        }
        client.close()
        return@withContext price
    }


}
