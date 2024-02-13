package com.example.viewhomework.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.viewhomework.R
import com.example.viewhomework.data.model.entityDB.NotesEntity
import com.example.viewhomework.view.fragment.AddCaseFragment
import com.example.viewhomework.view.fragment.ChangeFragment
import com.example.viewhomework.view.fragment.MainFragment

class MyRecyclerAdapter(
   val navigate: (NotesEntity) -> Unit
) : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

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
        holder.itemView.findViewById<TextView>(R.id.text_task).text = case.textCase

        with(holder.itemView) {
            findViewById<ConstraintLayout>(R.id.current_item).setOnClickListener {
                navigate(case)
            }
        }

    }

    override fun getItemCount() = noteList.size

    fun setData(notes: List<NotesEntity>) {
        this.noteList = notes
        notifyDataSetChanged()
    }

}