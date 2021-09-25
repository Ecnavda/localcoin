package com.example.localcoin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val confirmLoginButton = findViewById<Button>(R.id.confirmLoginButton)


        confirmLoginButton.setOnClickListener{
            val intention = Intent(this, PortfolioActivity::class.java)
            this.startActivity(intention)
        }
    }
}