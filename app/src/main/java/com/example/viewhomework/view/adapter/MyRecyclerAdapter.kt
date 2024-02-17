package com.example.viewhomework.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.viewhomework.R
import com.example.viewhomework.data.model.entityDB.NotesEntity
import com.example.viewhomework.view.fragment.MainFragment
import com.example.viewhomework.view.fragment.MainFragmentDirections


class MyRecyclerAdapter
    : ListAdapter<NotesEntity, MyRecyclerAdapter.MyViewHolder>(DiffUtilCallback) {

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(notesEntity: NotesEntity) {
            val constraint = view.findViewById<ConstraintLayout>(R.id.current_item)
            when (notesEntity.importance) {
                "Высокая" -> {
                    constraint.setBackgroundResource(R.drawable.style_recycler_view3)
                }
                "Низкая" -> {
                    constraint.setBackgroundResource(R.drawable.style_recycler_view2)
                }
                else -> constraint.setBackgroundResource(R.drawable.style_recycler_view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.case_cell, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val case = getItem(position)
        holder.itemView.findViewById<TextView>(R.id.text_task).text = case.textCase

        val viewHolder = MyViewHolder(holder.itemView)
        viewHolder.onBind(case)

        with(holder.itemView) {
            findViewById<ConstraintLayout>(R.id.current_item).setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToChangeFragment(case)
                findNavController().navigate(action)
            }
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<NotesEntity>() {
        override fun areItemsTheSame(oldItem: NotesEntity, newItem: NotesEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NotesEntity, newItem: NotesEntity): Boolean {
            return oldItem == newItem
        }

    }
}