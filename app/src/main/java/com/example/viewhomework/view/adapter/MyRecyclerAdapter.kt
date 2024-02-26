package com.example.viewhomework.view.adapter

import android.animation.AnimatorInflater
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.viewhomework.R
import com.example.viewhomework.data.model.entityDB.NotesEntity
import com.example.viewhomework.view.fragment.MainFragmentDirections
import com.example.viewhomework.view.viewModel.NotesViewModel


class MyRecyclerAdapter(private val notesViewModel: NotesViewModel)
    : ListAdapter<NotesEntity, MyRecyclerAdapter.MyViewHolder>(DiffUtilCallback) {

    class MyViewHolder(private val view: View, private val notesViewModel: NotesViewModel)
        : RecyclerView.ViewHolder(view) {

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

        fun onCheck(checkBox: CheckBox, note: NotesEntity, textView: TextView) {
            if (checkBox.isChecked) {
                note.check = true
                textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                notesViewModel.updateNote(note)
            } else {
                textView.paintFlags = 0
                note.check = false
                notesViewModel.updateNote(note)
            }
        }

        fun animButton(note: NotesEntity, itemView: View, dateField: ConstraintLayout) {
            val animation = AnimatorInflater.loadAnimator(itemView.context, R.animator.anim_button_start)
            animation.setTarget(dateField)
            animation.start()

            dateField.visibility = View.VISIBLE
            val dateCreate = itemView.findViewById<TextView>(R.id.date_create)
            dateCreate.text = note.dateCreate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.case_cell, parent, false)

        return MyViewHolder(view, notesViewModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val case = getItem(position)
        val textView = holder.itemView.findViewById<TextView>(R.id.text_task)
        textView.text = case.textCase
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.checked_box)

        if (case.check == true) {
            checkBox.isChecked = true
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        else {
            checkBox.isChecked = false
            textView.paintFlags = 0
        }

        val viewHolder = MyViewHolder(holder.itemView, notesViewModel)
        viewHolder.onBind(case)

        val currentItem = holder.itemView.findViewById<ConstraintLayout>(R.id.current_item)
        currentItem.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToChangeFragment(case)
            holder.itemView.findNavController().navigate(action)
        }

        checkBox.setOnClickListener {
            viewHolder.onCheck(checkBox, case, textView)
        }

        val dateField = holder.itemView.findViewById<ConstraintLayout>(R.id.constraint_date_create)
        val infoIcon = holder.itemView.findViewById<ImageView>(R.id.info_icon)
        infoIcon.setOnClickListener {
            viewHolder.animButton(case, holder.itemView, dateField)
        }

        dateField.setOnClickListener {
            val animation = AnimatorInflater.loadAnimator(
                holder.itemView.context,
                R.animator.anim_button_end)
            animation.setTarget(dateField)
            animation.start()
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