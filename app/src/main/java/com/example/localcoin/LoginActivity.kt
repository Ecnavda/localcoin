package com.example.localcoin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val confirmLoginButton = findViewById<ImageView>(R.id.confirmLoginButton)
        val backButton = findViewById<ImageView>(R.id.backButton)


        confirmLoginButton.setOnClickListener{
            val intention = Intent(this, accountActivity::class.java)
            this.startActivity(intention)

            backButton.setOnClickListener {
                val intention = Intent(this,MainActivity::class.java)
                this.startActivity(intention)
            }
        }
    }
}