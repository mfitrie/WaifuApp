package com.example.waifuapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.waifuapp.Repository.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val repository = Repository()
        val viewModelFactory = MainViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        getPic(viewModel)

        btn_get.setOnClickListener {
            getPic(viewModel)


        }


    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkInternetConnection(): Boolean {
        val connectionManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectionManager.let {
            val capabilities =
                connectionManager.getNetworkCapabilities(connectionManager.activeNetwork)
            capabilities.let {
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true
                    }
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true
                    }

                }
            }
        }
        return false
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun getPic(viewModel: MainViewModel) {



        progressBar.visibility = View.VISIBLE
        Log.d("PROGRESS_BAR", "getPic: progressbar visibile")

        if (checkInternetConnection()) {
            viewModel.getWaifu()
            viewModel.waifuResponse.observe(this, Observer { response ->
                if (response.isSuccessful) {

                    // test code
//                    val data = response.body()?.images.toString().split(", ")[12].split("url=")[1].split(")]")[0]

                    val data = response.body()?.images?.get(0)?.url

                    Log.d("RESPONSE", data.toString())

                    Picasso.with(this).load(data).into(im_WaifuPic)

                    progressBar.visibility = View.GONE
                    Log.d("PROGRESS_BAR", "getPic: progressbar gone")


                } else {
                    Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
                }
            })


        } else {
            Toast.makeText(this, "Please turn on your internet connection", Toast.LENGTH_SHORT)
                .show()
        }

    }


}