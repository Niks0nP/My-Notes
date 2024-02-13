package com.example.viewhomework.view.fragment

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viewhomework.data.model.entityDB.NotesEntity
import com.example.viewhomework.databinding.ChangeCaseBinding
import com.example.viewhomework.view.viewModel.NotesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class ChangeFragment: Fragment() {

    private var _binding: ChangeCaseBinding? = null
    private val binding get() = _binding!!

    var id: Long = 0

    private lateinit var nNotesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChangeCaseBinding.inflate(inflater, container, false)
        id = arguments?.getLong("caseId") ?: -1L

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var note: NotesEntity

        nNotesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        if (id.toInt() != -1) {
            CoroutineScope(Main).launch {
                note = nNotesViewModel.updateElement(id)
                binding.changeTextField.setText(note.textCase)
//                binding.changeSpinnerView.setPromptId(0)
                binding.dateEditField.setText(note.deadLine)
            }
        }
    }
}