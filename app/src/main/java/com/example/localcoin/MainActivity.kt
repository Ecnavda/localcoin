package com.example.localcoin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity() {


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.



        setContentView(R.layout.activity_main)

        val loginButton = findViewById<ImageView>(R.id.loginButton)
        val signupButton = findViewById<ImageView>(R.id.signupButton)


        loginButton.setOnClickListener{
            val intention = Intent(this, LoginActivity::class.java)
            this.startActivity(intention)
        }


    }
}