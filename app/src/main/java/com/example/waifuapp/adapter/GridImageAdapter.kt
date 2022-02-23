package com.example.waifuapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.waifuapp.R
import com.example.waifuapp.model.WaifuDB.WaifuDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_waifu_save.view.*
import kotlinx.android.synthetic.main.item_grid_view.view.*

class GridImageAdapter(val waifuDB: List<WaifuDB>, val context: Context) : RecyclerView.Adapter<GridImageAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(waifuDB[position].url).into(holder.itemView.iv_item_grid)
    }

    override fun getItemCount(): Int {
        return waifuDB.size
    }


}