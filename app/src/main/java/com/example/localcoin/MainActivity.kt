package com.example.localcoin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity() {


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<ImageView>(R.id.loginButton)
        val signupButton = findViewById<ImageView>(R.id.signupButton)

        loginButton.setOnClickListener{
            val intention = Intent(this, LoginActivity::class.java)
            this.startActivity(intention)
        }
    }
}