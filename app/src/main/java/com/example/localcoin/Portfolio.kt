package com.example.localcoin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class Portfolio : Fragment() {
    val FILE_NAME = "LatestCrypto.txt"
    val listOfCoins = mutableListOf<CoinItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_portfolio, container, false)
        getData(view)
        var my_recycler = view.findViewById<RecyclerView>(R.id.coinRecycler)
        val adapter = CoinAdapter(listOfCoins)
        LinearLayoutManager(this.context).also {
            if (my_recycler != null) {
                my_recycler.layoutManager = it
            }
        }

        my_recycler?.adapter = adapter

        return view
    }

    fun getData(v: View){
        try {
            val fis = context?.openFileInput(FILE_NAME)
            val irs = InputStreamReader(fis)
            val br = BufferedReader(irs)
            val line_stream = br.lines()
            line_stream.forEach {
                val crypto_json = JSONObject(it)
                if (crypto_json.get("coinName").toString().isNotEmpty() && crypto_json.get("quantity").toString().isNotEmpty()) {
                    listOfCoins.add(
                        CoinItem(
                            crypto_json.get("coinName").toString(),
                            crypto_json.get("quantity").toString().toDouble()
                        )
                    )
                }
            }
            //println(v.findViewById<Button>(R.id.portfolioPriceButton).visibility)// = View.VISIBLE
            /*
            var line : String? = ""
            line = br.readLine()

            if ( br.readLine() != null){

            }
            else {
                while (line != null) {
                    var name_temp = line.split("\\s".toRegex())[0]
                    var qty_temp  = (line.split("\\s".toRegex())[1]).toInt()
                    var amount_temp =  (line.split("\\s".toRegex())[2]).toDouble()
                    var coin_temp = CoinItem(name_temp,qty_temp,amount_temp )
                    listOfCoins.add(coin_temp)
                    line = br.readLine()
                }
            }
            */

            if (fis != null) {
                fis.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun getPrice(v: View) {
        var coinArray = ArrayList<String>()
        val api = API()
        val fis = v.context?.openFileInput(FILE_NAME)
        val irs = InputStreamReader(fis)
        val br = BufferedReader(irs)
        val line_stream = br.lines()
        for (i in 1..3) {
            val crypto_json = JSONObject(br.readLine())
            if (crypto_json.get("coinName").toString().isNotEmpty() && crypto_json.get("quantity").toString().isNotEmpty()) {
                val coin = crypto_json.get("coinName").toString() + "-USD"
                coinArray.add(coin)

            }
        }
    }
}