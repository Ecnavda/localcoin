package com.example.localcoin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

class CoinAdapter (private val coins: List<CoinItem>)  : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>(){

    class CoinViewHolder(view: View): RecyclerView.ViewHolder(view){
        val coinName: TextView = view.findViewById(R.id.coin_Name)
        val qtyName: TextView = view.findViewById(R.id.qty_Coin)
        val totalAmount: TextView = view.findViewById(R.id.totalAmount)

        init{

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_row, parent,false)

        return  CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin : CoinItem = coins[position]
        holder.coinName.text = coin.name
        holder.qtyName.text = coin.qyt.toString()
        holder.totalAmount.text = coin.totalAmount.toString()

    }
    override fun getItemCount(): Int {
        return 8
    }


}