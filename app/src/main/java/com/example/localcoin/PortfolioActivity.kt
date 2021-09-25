package com.example.localcoin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PortfolioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio)

        val addCoinButton = findViewById<Button>(R.id.addCoin)

        addCoinButton.setOnClickListener{
            val intention = Intent(this, AddCoinActivity::class.java)
            this.startActivity(intention)
        }
    }
}