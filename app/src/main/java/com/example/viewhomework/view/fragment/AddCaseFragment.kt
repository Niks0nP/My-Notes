package com.example.viewhomework.view.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        binding.closeButton.setOnClickListener {
            findNavController().navigate(R.id.action_addCaseFragment_to_mainFragment)
        }

        binding.switchDate.setOnCheckedChangeListener { _, isChecked ->
            setCalendarDate(isChecked)
        }

        binding.saveButton.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val textCase = binding.editTextField.text.toString()
        val importance = binding.spinnerView.selectedItem.toString()
        val deadline = binding.dateEditField.text.toString()
        val dateCreate: String = LocalDate.now()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

        if (checkOnNull(textCase, deadline, importance)) {
            val notes = NotesEntity(0, textCase, deadline, importance, dateCreate, check = null)
            nNotesViewModel.insertNewNotes(notes)

            findNavController().navigate(R.id.action_addCaseFragment_to_mainFragment)
        }
    }

    private fun setCalendarDate(isChecked: Boolean) {
        if (isChecked){
            binding.calendarConstraint.visibility = View.VISIBLE
            binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                binding.dateEditField.text = "$dayOfMonth.${month + 1}.$year"
            }

            binding.textOk.setOnClickListener {
                binding.calendarConstraint.visibility = View.GONE
            }
        } else {
            binding.calendarConstraint.visibility = View.GONE
            binding.dateEditField.text = null
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