package com.example.viewhomework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter(private val list: List<String>) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = view.findViewById(R.id.text_task)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        println("onCreateView")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.case_cell, parent, false)

        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        println("onBindView")
        holder.textView.text = list[position]
    }

    override fun getItemCount() = list.size


}