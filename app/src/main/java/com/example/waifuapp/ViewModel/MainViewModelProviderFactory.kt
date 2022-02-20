package com.example.waifuapp.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.waifuapp.Repository.Repository
import java.lang.Appendable

class MainViewModelProviderFactory (private val repository: Repository, val context: Application): ViewModelProvider.AndroidViewModelFactory(context) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository, context) as T
    }
}