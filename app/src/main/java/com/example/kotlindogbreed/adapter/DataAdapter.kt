package com.example.kotlindogbreed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindogbreed.R
import com.example.kotlindogbreed.room.Product

class DataAdapter(var context: Context, var postList: List<Product>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.breed_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (postList.isNotEmpty()) {
            holder.tvTitle.text = postList[position].fName + "-" + postList[position].lName
        }

    }

    override fun getItemCount(): Int {
        return if (postList.size == 0) {
            1
        } else {
            postList.size
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.item_name)

        }
    }

}