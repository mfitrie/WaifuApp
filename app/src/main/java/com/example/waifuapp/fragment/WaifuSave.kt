package com.example.waifuapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.waifuapp.R
import com.example.waifuapp.Repository.DatabaseRepository
import com.example.waifuapp.Repository.Repository
import com.example.waifuapp.ViewModel.MainViewModel
import com.example.waifuapp.ViewModel.MainViewModelProviderFactory
import com.example.waifuapp.adapter.GridImageAdapter
import com.example.waifuapp.model.WaifuDB.WaifuDB
import kotlinx.android.synthetic.main.fragment_waifu_save.*

class WaifuSave : Fragment(), GridImageAdapter.OnItemClickListener {

    private lateinit var viewModel: MainViewModel

    // from interface in Recyclerview adapter
    override fun onItemClicked(waifu: WaifuDB) {
        val dialogFragment = DialogWaifuLiked()
        dialogFragment.show(parentFragmentManager, "dialogFragment_waifu")

        // set data to dialogFragment
        viewModel.setData(waifu)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_waifu_save, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainViewModelProviderFactory(repository, requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)


        viewModel.readAllData.observe(viewLifecycleOwner, Observer { response ->
            val adapter = GridImageAdapter(response, this)
            val gridLayout = GridLayoutManager(context, 2)
            rv_likeHolder.layoutManager = gridLayout
            rv_likeHolder.adapter = adapter

        })
    }

}