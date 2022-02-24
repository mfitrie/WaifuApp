package com.example.waifuapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.waifuapp.R
import com.example.waifuapp.Repository.Repository
import com.example.waifuapp.ViewModel.MainViewModel
import com.example.waifuapp.ViewModel.MainViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_dialog_waifu_liked.*
import kotlinx.android.synthetic.main.fragment_waifu_pic.*


class DialogWaifuLiked : DialogFragment() {

private lateinit var viewModel: MainViewModel
private lateinit var urlData: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_waifu_liked, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_fragment_background_corner)

        val repository = Repository()
        val viewModelFactory = MainViewModelProviderFactory(repository, requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner, Observer { data ->
            urlData = data.url

            Glide.with(this).load(data.url).into(iv_waifuLike)
            tv_quoteLike.text = data.quote

            Log.d("DIALOG_FRAGMENT", "onViewCreated: ${data.url}")
        })


        btn_fragmentDownload.setOnClickListener{
            downloadImage(urlData)
        }





    }

    private fun downloadImage(url: String) {

        if(url != null){
            viewModel.downloadWaifu(url)
            Toast.makeText(context, "Successfully downloaded", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show()
        }

    }
}