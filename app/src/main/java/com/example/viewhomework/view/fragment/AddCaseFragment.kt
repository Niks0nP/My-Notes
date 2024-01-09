package com.example.viewhomework.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.viewhomework.R
import com.example.viewhomework.data.repository.TodoItemsRepository
import com.example.viewhomework.databinding.AddCaseBinding
import com.example.viewhomework.view.App
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddCaseFragment : Fragment() {

    private var _binding: AddCaseBinding? = null
    private val binding get() = _binding!!

    private val todoItemsRepository: TodoItemsRepository?
        get() = (activity?.applicationContext as? App)?.todoItemsRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddCaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var textCase: String
        var deadline: String = ""
        var importance: String

        var dateCreate: String = LocalDate.now()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

        binding.closeButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_view, MainFragment())
            fragmentTransaction.commit()
        }

        binding.switchDate.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.calendarLayout.visibility = View.VISIBLE
                binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                    binding.dateEditField.text = "$dayOfMonth.${month + 1}.$year"
                    deadline = "$dayOfMonth.$month.$year"
                }

                binding.textOk.setOnClickListener {
                    binding.calendarLayout.visibility = View.INVISIBLE
                }
            } else {
                binding.calendarLayout.visibility = View.INVISIBLE
                binding.dateEditField.text = null
            }
        }

        binding.saveButton.setOnClickListener {
            textCase = binding.editTextField.text.toString()
            importance = binding.spinnerView.selectedItem.toString()
            todoItemsRepository?.addCase(textCase, importance, deadline, dateCreate)

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_view, MainFragment())
            fragmentTransaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}