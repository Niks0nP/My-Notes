package com.example.viewhomework.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewhomework.R
import com.example.viewhomework.data.model.TodoItem
import com.example.viewhomework.data.model.entityDB.NotesEntity

class MyRecyclerAdapter() : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    private var noteList = emptyList<NotesEntity>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.case_cell, parent, false)

        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val case = noteList[position]
        val context = holder.itemView.context

//        with(holder.itemView){
//            findViewById<TextView>(R.id.text_task).text = case.textCase
//        }

        holder.itemView.findViewById<TextView>(R.id.text_task).text = case.textCase
    }

    override fun getItemCount() = noteList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(notes: List<NotesEntity>) {
        this.noteList = notes
        notifyDataSetChanged()
    }

}