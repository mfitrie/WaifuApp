package com.example.waifuapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.waifuapp.R
import com.example.waifuapp.fragment.DialogWaifuLiked
import com.example.waifuapp.model.WaifuDB.WaifuDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_waifu_save.view.*
import kotlinx.android.synthetic.main.item_grid_view.view.*

class GridImageAdapter(val waifuDB: List<WaifuDB>, val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<GridImageAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(waifu: WaifuDB, clickListener: OnItemClickListener){
            itemView.waifu_id.text = waifu.waifu_ID.toString()
            Glide.with(itemView.context)
                .load(waifu.url)
                .into(itemView.iv_item_grid)

            itemView.setOnClickListener{
                clickListener.onItemClicked(waifu)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Glide.with(holder.itemView).load(waifuDB[position].url).into(holder.itemView.iv_item_grid)
//        holder.itemView.waifu_id.text = waifuDB[position].waifu_ID.toString()

//        holder.itemView.setOnClickListener {
//            Toast.makeText(context, "${waifuDB[position].waifu_ID} clicked", Toast.LENGTH_SHORT).show()
//            val dialogWaifu = DialogWaifuLiked()
//        }

        val waifu = waifuDB[position]
        holder.bind(waifu, itemClickListener)
    }

    override fun getItemCount(): Int {
        return waifuDB.size
    }



    interface OnItemClickListener{
        fun onItemClicked(waifu: WaifuDB)
    }

}