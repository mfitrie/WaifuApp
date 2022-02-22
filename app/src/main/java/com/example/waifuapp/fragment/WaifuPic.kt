package com.example.waifuapp.fragment

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.waifuapp.ViewModel.MainViewModel
import com.example.waifuapp.ViewModel.MainViewModelProviderFactory
import com.example.waifuapp.R
import com.example.waifuapp.Repository.Repository
import kotlinx.android.synthetic.main.fragment_waifu_pic.*
import java.util.jar.Manifest

class WaifuPic : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val PERMISSION_ID = 10
    private lateinit var data: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_waifu_pic, container, false)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory =
            MainViewModelProviderFactory(repository, requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        getPic(viewModel)

        btn_get.setOnClickListener {
            getPic(viewModel)
        }

        btn_gif.setOnClickListener {
            getGif(viewModel)
        }


        // download
        btn_download.setOnClickListener {
            if(checkPermission()){
                if(checkInternetConnection()){
                    downloadImage(data)
                    Toast.makeText(context, "Successfully download", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Please turn on your internet connection",Toast.LENGTH_SHORT).show()
                }

            }
        }


    }


    //    Context.CONNECTIVITY_SERVICE
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkInternetConnection(): Boolean {
        val connectionManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
            viewModel.waifuResponse.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {

                    // test code
//                    val data = response.body()?.images.toString().split(", ")[12].split("url=")[1].split(")]")[0]
                    data = response.body()?.images?.get(0)?.url.toString()
                    Log.d("RESPONSE", data.toString())
                    Glide.with(this).load(data).into(im_WaifuPic)

                    progressBar.visibility = View.GONE
                    Log.d("PROGRESS_BAR", "getPic: progressbar gone")




                } else {
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show()
                    Log.d("FAILED", "getPic: ${response.code()}")
                }
            })


            // Anime Quote
            viewModel.getAnimeQuote()
            viewModel.animeQuoteResponse.observe(viewLifecycleOwner, Observer { response ->
                if(response.isSuccessful){
                    val data = response.body()?.quote
                    tv_animeQuote.text = data.toString()
                }else{
                    tv_animeQuote.text = "We can't waste time Worrying about the what if's"
                }
            })


        } else {
            Toast.makeText(requireContext(), "Please turn on your internet connection",Toast.LENGTH_SHORT).show()
        }

    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun getGif(viewModel: MainViewModel) {
        progressBar.visibility = View.VISIBLE
        Log.d("PROGRESS_BAR", "getPic: progressbar visibile")

        if (checkInternetConnection()) {
            viewModel.getWaifuGif(true)
            viewModel.waifuResponseGif.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {

                    // test code
//                    val data = response.body()?.images.toString().split(", ")[12].split("url=")[1].split(")]")[0]

                    Log.d("INSIDE_GIF_SCOPE", "getGif: Im clicked")
                    data = response.body()?.images?.get(0)?.url.toString()
                    Log.d("RESPONSE", data.toString())
                    Glide.with(this).load(data).into(im_WaifuPic)

                    progressBar.visibility = View.GONE
                    Log.d("PROGRESS_BAR", "getPic: progressbar gone")


                } else {
                    Toast.makeText(requireContext(), response.code(), Toast.LENGTH_SHORT).show()
                }
            })


            // Anime Quote
            viewModel.getAnimeQuote()
            viewModel.animeQuoteResponse.observe(viewLifecycleOwner, Observer { response ->
                if(response.isSuccessful){
                    val data = response.body()?.quote
                    tv_animeQuote.text = data.toString()
                }else{
                    tv_animeQuote.text = "We can't waste time Worrying about the what if's"
                }
            })



        } else {
            Toast.makeText(
                requireContext(),
                "Please turn on your internet connection",
                Toast.LENGTH_SHORT
            )
                .show()
        }

    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun downloadImage(url: String) {
        if (checkInternetConnection()) {
            viewModel.downloadWaifu(url)
        }
    }




    private fun checkPermission():Boolean{
        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_ID)
            return false
        }
//        Toast.makeText(requireContext(), "Permission granted", Toast.LENGTH_SHORT).show()
        return true


    }




}