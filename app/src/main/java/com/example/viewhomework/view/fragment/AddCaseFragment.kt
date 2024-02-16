package com.example.viewhomework.view.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viewhomework.R
import com.example.viewhomework.data.model.entityDB.NotesEntity
import com.example.viewhomework.databinding.AddCaseBinding
import com.example.viewhomework.view.viewModel.NotesViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddCaseFragment : Fragment() {

    private var _binding: AddCaseBinding? = null
    private val binding get() = _binding!!

    private lateinit var nNotesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddCaseBinding.inflate(inflater, container, false)

        nNotesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var textCase: String
        var deadline = ""
        var importance: String

        val dateCreate: String = LocalDate.now()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

        binding.closeButton.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_view, MainFragment())
                .commit()
        }

        binding.switchDate.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.calendarConstraint.visibility = View.VISIBLE
                binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                    binding.dateEditField.text = "$dayOfMonth.${month + 1}.$year"
                    deadline = "$dayOfMonth.${month + 1}.$year"
                }

                binding.textOk.setOnClickListener {
                    binding.calendarConstraint.visibility = View.INVISIBLE
                }
            } else {
                binding.calendarConstraint.visibility = View.INVISIBLE
                binding.dateEditField.text = null
            }
        }

        binding.saveButton.setOnClickListener {
            textCase = binding.editTextField.text.toString()
            importance = binding.spinnerView.selectedItem.toString()

            insertDataToDatabase(textCase, deadline, importance)
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_view, MainFragment())
                .commit()
        }
    }

    private fun insertDataToDatabase(textCase: String, deadline: String, importance: String) {
        if (checkOnNull(textCase, deadline, importance)) {
            val notes = NotesEntity(0, textCase, deadline, importance)
            nNotesViewModel.insertNewNotes(notes)
        }
    }

    private fun checkOnNull(textCase: String, deadline: String, importance: String): Boolean {
        return !(TextUtils.isEmpty(textCase) && TextUtils.isEmpty(deadline) && TextUtils.isEmpty(importance))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}