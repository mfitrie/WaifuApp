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
import com.bumptech.glide.Glide
import com.example.waifuapp.Repository.Repository
import com.example.waifuapp.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        view_pager2.adapter = viewPagerAdapter

        TabLayoutMediator(tab_layout, view_pager2){ tab, position ->
            when(position){
                0->{
                    tab.text = "Waifu Pic"
                    tab.setIcon(R.drawable.ic_anime_icon)
                }
                1 ->{
                    tab.text = "Like pic"
                    tab.setIcon(R.drawable.ic_like_icon)
                }
                else->{
                    tab.text = "Waifu Pic"
                }
            }
//            tab.text = "Tab ${position + 1}"
        }.attach()
    }





}