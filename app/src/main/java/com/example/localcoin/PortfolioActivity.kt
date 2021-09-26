package com.example.localcoin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

class PortfolioActivity : AppCompatActivity() {
    val FILE_NAME = "test_list1.txt"
    val listOfCoins = mutableListOf<CoinItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio)

        val addCoinButton = findViewById<Button>(R.id.addCoin)
        try {
5
            val fis = openFileInput(FILE_NAME)
            val irs = InputStreamReader(fis)
            val br = BufferedReader(irs)
            val sb = StringBuilder()
            println("Does it read?")
            var text : String? = ""
            var line : String? = ""
            line = readLine()
            if ( br.readLine() == null){

            } else {
                while (line != null) {
                    var name_temp = line.split("\\s".toRegex())[0]
                    println(name_temp)
                    var qty_temp  = (line.split("\\s".toRegex())[1]).toInt()
                    println(qty_temp)
                    var amount_temp =  (line.split("\\s".toRegex())[2]).toDouble()
                    println(amount_temp)

                    var coin_temp = CoinItem(name_temp,qty_temp,amount_temp )
                    listOfCoins.add(coin_temp)
                    line = br.readLine()
                }

            }

            fis.close()

        } catch (e: IOException) {
            println("Failed to open file HERE")
            e.printStackTrace()
        }

        addCoinButton.setOnClickListener{

            val intention = Intent(this, AddCoinActivity::class.java)
            this.startActivity(intention)
        }
        //val recyclerView = findViewById<RecyclerView>(R.id.coinRecycler)
        //val adapter = CoinAdapter(listOfCoins)

        //recyclerView.adapter
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)



    }


}