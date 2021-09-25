package com.example.localcoin

import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.io.*
import java.lang.StringBuilder

class AddCoinActivity : AppCompatActivity() {
    val FILE_NAME = "test_3.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coin)

        val coinNameTextView = findViewById<TextView>(R.id.coinName)
        val numbCointextView = findViewById<TextView>(R.id.numberOfCoin)
        val getCoinButton = findViewById<Button>(R.id.getCoinB)



        getCoinButton.setOnClickListener {
            val coinName = coinNameTextView.text.toString()
            val numberCoin = numbCointextView.text.toString()
            println(coinName)
            println("I am dumb")
            try {
                val fos = openFileOutput(FILE_NAME,  MODE_APPEND)
                fos.write(coinName.toByteArray())
                fos.write(" ".toByteArray())
                fos.write(numberCoin.toByteArray())
                fos.write("\n".toByteArray())
                fos.close()

            } catch (e: IOException) {
                println("Failed to open file")
                e.printStackTrace()
            }

            val int = Intent(this, PortfolioActivity::class.java)

            this.startActivity(int)

        }


    }
}