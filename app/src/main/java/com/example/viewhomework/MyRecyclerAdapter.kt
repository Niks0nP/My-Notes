package com.example.viewhomework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter() :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    var data: List<TodoItem> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val textView: TextView
//        init {
//            textView = view.findViewById(R.id.text_task)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.case_cell, parent, false)

        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val case = data[position]
        val context = holder.itemView.context

        with(holder.itemView){
            findViewById<TextView>(R.id.text_task).text = case.caseText
        }
//        holder.textView.text = list[position]
    }

    override fun getItemCount() = data.size


}