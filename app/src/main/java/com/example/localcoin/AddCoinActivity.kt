package com.example.localcoin

import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.FileOutputStream

class AddCoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coin)

        val coinNameTextView = findViewById<TextView>(R.id.coinName)
        val numbCointextView = findViewById<TextView>(R.id.numberOfCoin)
        val getCoinButton = findViewById<Button>(R.id.getCoinB)



        getCoinButton.setOnClickListener {
            val coinName = coinNameTextView.getText().toString()
            val numberCoin = numbCointextView.getText().toString()

            val assetManager: AssetManager = this.assets


            val int = Intent(this, PortfolioActivity::class.java)
            this.startActivity(int)

        }


    }
}