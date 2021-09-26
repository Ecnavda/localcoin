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
import java.lang.Error

// NOTE: EAI_NOADDRESS error
// https://stackoverflow.com/questions/6355498/unable-to-resolve-host-url-here-no-address-associated-with-host-name

class API : ViewModel() {
    private val base_url = "portal.coinroutes.com"

    fun launchDataLoad(c: String, q: String, v: TextView) {
        viewModelScope.launch {
        val price = streamPrice(c, q)
        v.text = price
        }
    }

    suspend fun streamPrice(currency_pair: String, quantity: String) : String = withContext(Dispatchers.IO) {
        var finalPrice = ""
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
            try {
                send(RealPriceRequest.toString())
                val mesg = incoming.receive() as? Frame.Text
                println(mesg?.readText())
                if (JSONObject(mesg?.readText().toString()).has("price")) {
                    val convert_me_daddy = JSONObject(mesg?.readText().toString()).get("price")

                    finalPrice = "$%.2f".format(convert_me_daddy)
                }
                if (JSONObject(mesg?.readText().toString()).has("error")) {
                    val result = JSONObject(mesg?.readText().toString()).get("error")
                    finalPrice = result.toString()
                }
            }
            catch (e: Error) {
                println(e)

            }
        }
        client.close()
        return@withContext finalPrice
    }


}
