package com.example.viewhomework.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.viewhomework.R
import com.example.viewhomework.data.model.entityDB.NotesEntity
import com.example.viewhomework.databinding.ChangeCaseBinding
import com.example.viewhomework.view.viewModel.NotesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class ChangeFragment: Fragment() {

    private var _binding: ChangeCaseBinding? = null
    private val binding get() = _binding!!

    var id: Long = 0

    private lateinit var nNotesViewModel: NotesViewModel

    private val args by navArgs<ChangeFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChangeCaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nNotesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        binding.changeTextField.setText(args.currentNote.textCase)
        binding.dateEditField.setText(args.currentNote.deadLine)
        checkImportance(args.currentNote.importance)

        binding.changeButton.setOnClickListener {
            updateNote()
            findNavController().navigate(R.id.action_changeFragment_to_mainFragment)
        }

        binding.closeButton.setOnClickListener {
            findNavController().navigate(R.id.action_changeFragment_to_mainFragment)
        }

        binding.deleteButton.setOnClickListener {
            deleteNote(args.currentNote.id)
        }
    }

    private fun checkImportance(importance: String) {
        when (importance) {
            "Низкая" -> {
                binding.changeSpinnerView.setSelection(1)
            }
            "Высокая" -> {
                binding.changeSpinnerView.setSelection(2)
            }
            else -> binding.changeSpinnerView.setSelection(0)
        }
    }

    private fun updateNote() {
        val textNote = binding.changeTextField.text.toString()
        val importance = binding.changeSpinnerView.selectedItem.toString()
        val deadline = binding.dateEditField.text.toString()

        if (checkOnNull(textNote, deadline, importance)) {
            val updateNote = NotesEntity(id,textNote, deadline, importance)
            nNotesViewModel.updateNote(updateNote)
        }
    }

    private fun checkOnNull(textCase: String, deadline: String, importance: String): Boolean {
        return !(TextUtils.isEmpty(textCase) && TextUtils.isEmpty(deadline) && TextUtils.isEmpty(importance))
    }

    private fun deleteNote(id: Long) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") {_,_ ->
            nNotesViewModel.deleteNote(id)
            findNavController().navigate(R.id.action_changeFragment_to_mainFragment)
        }
        builder.setNegativeButton("Нет") { _,_ -> }
        builder.setTitle("Удалить заметку?")
        builder.setMessage("Вы действительно хотите удалить заметку?")
        builder.create().show()
    }
}