package com.example.waifuapp.adapter

import android.net.wifi.aware.WifiAwareNetworkInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.waifuapp.fragment.WaifuPic
import com.example.waifuapp.fragment.WaifuSave

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                WaifuPic()
            }
            1->{
                WaifuSave()
            }
            else->{
                Fragment()
            }
        }
    }
}