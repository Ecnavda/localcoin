package com.example.localcoin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddCoin.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCoin : Fragment() {
    val FILE_NAME = "test_newFile2.txt"
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_coin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coinQuantity = view.findViewById<TextView>(R.id.coinQuantity)
        val coinSpin = view.findViewById<Spinner>(R.id.coinNameSpin)
        val coinButton = view.findViewById<ImageView>(R.id.coinAddButton)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.coin_spinner,
            R.layout.spinner_style
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            coinSpin.adapter = adapter
        }

        coinButton.setOnClickListener {
            //val coinName = coinNameTextView.text.toString()
            val coinName = coinSpin.selectedItem.toString()
            val numberCoin = coinQuantity.text.toString()
            println(coinName)
            println("I am dumb")
            try {
                val fos = view.context.openFileOutput(FILE_NAME, AppCompatActivity.MODE_APPEND)
                fos.write(coinName.toByteArray())
                fos.write(" ".toByteArray())
                fos.write(numberCoin.toByteArray())
                fos.write(" ".toByteArray())
                fos.write("45".toByteArray())
                fos.write("\n".toByteArray())
                fos.close()

            } catch (e: IOException) {
                println("Failed to open file")
                e.printStackTrace()
            }

            Navigation.findNavController(view).navigate(R.id.action_addCoin2_to_portfolio)


        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddCoin.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddCoin().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}