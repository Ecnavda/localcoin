package com.example.localcoin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

class PortfolioActivity : AppCompatActivity() {
    val FILE_NAME = "test_3.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio)

        val addCoinButton = findViewById<Button>(R.id.addCoin)
        try {
            val fis = openFileInput(FILE_NAME)
            val irs = InputStreamReader(fis)
            val br = BufferedReader(irs)
            val sb = StringBuilder()
            println("Does it read?")
            var text : String? = ""
            var line : String? = ""
            if ( br.readLine() == null){

            } else {
                while (line != null) {
                    text += line
                    line = br.readLine()
                    text += "\n"
                }
                var listC = findViewById<TextView>(R.id.listOfCoins)
                listC.text = text
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
    }
}