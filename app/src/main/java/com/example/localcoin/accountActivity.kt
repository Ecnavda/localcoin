package com.example.localcoin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageButton
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation

class accountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        findViewById<ImageButton>(R.id.accountNavbarAddCoinButton).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, AddCoin())
                .commit()
        }
        findViewById<ImageButton>(R.id.accountNavbarPortfolioButton).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, Portfolio())
                .commit()
        }
        findViewById<ImageButton>(R.id.accountNavbarSearchButton).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, SearchFragment())
                .commit()
        }
        findViewById<ImageButton>(R.id.accountNavbarProfileButton).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, ProfileFragment())
                .commit()
        }
    }
}
