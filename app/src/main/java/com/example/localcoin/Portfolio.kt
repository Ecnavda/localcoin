package com.example.localcoin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class Portfolio : Fragment() {
    val FILE_NAME = "test_newFile2.txt"
    val listOfCoins = mutableListOf<CoinItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_portfolio, container, false)
        getData()
        var my_recycler = view.findViewById<RecyclerView>(R.id.coinRecycler)
        val adapter = CoinAdapter(listOfCoins)
        LinearLayoutManager(this.context).also {
            if (my_recycler != null) {
                my_recycler.layoutManager = it
            }
        }

        my_recycler?.adapter = adapter

        return view
    }

    fun getData(){
        try {
            val fis = context?.openFileInput(FILE_NAME)
            val irs = InputStreamReader(fis)
            val br = BufferedReader(irs)
            val sb = StringBuilder()
            println("Does it read?")
            var text : String? = ""
            var line : String? = ""
            line = br.readLine()
            println(line)
            if ( br.readLine() == null){

            } else {
                while (line != null) {
                    println(line)
                    var name_temp = line.split("\\s".toRegex())[0]
                    var qty_temp  = (line.split("\\s".toRegex())[1]).toInt()
                    println(qty_temp)
                    var amount_temp =  (line.split("\\s".toRegex())[2]).toDouble()
                    println(amount_temp)

                    var coin_temp = CoinItem(name_temp,qty_temp,amount_temp )
                    listOfCoins.add(coin_temp)
                    line = br.readLine()
                }
            }

            if (fis != null) {
                fis.close()
            }
        } catch (e: IOException) {
            println("Failed to open file HERE")
            e.printStackTrace()
        }
    }
}