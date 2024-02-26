package com.example.viewhomework.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewhomework.view.adapter.MyRecyclerAdapter
import com.example.viewhomework.R
import com.example.viewhomework.data.model.entityDB.NotesEntity
import com.example.viewhomework.databinding.MainScreenBinding
import com.example.viewhomework.view.viewModel.NotesViewModel

class MainFragment : Fragment() {

    private var _binding: MainScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    private lateinit var nNotesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainScreenBinding.inflate(inflater, container, false)
        nNotesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        myRecyclerAdapter = MyRecyclerAdapter(nNotesViewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addCaseFragment)
        }

        binding.deleteBtnMainScreen.setOnClickListener {
            deleteAllNotes()
        }

        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.myRecyclerView.adapter = myRecyclerAdapter

        nNotesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        nNotesViewModel.readAllData.observe(viewLifecycleOwner) { notes ->
            myRecyclerAdapter.submitList(notes)
            quantityNotes(notes)
        }
    }

    private fun deleteAllNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") {_,_ ->
            nNotesViewModel.deleteAllNotes()
        }
        builder.setNegativeButton("Нет") { _,_ -> }
        builder.setTitle("Удалить все заметки?")
        builder.setMessage("Вы действительно хотите удалить все заметки?")
        builder.create().show()
    }

    private fun quantityNotes(notes: List<NotesEntity>) {
        when (notes.size) {
            1 -> {
                binding.quantityNotes.text = getString(R.string.note_size1, notes.size.toString())
            }
            2,3,4 -> {
                binding.quantityNotes.text = getString(R.string.note_size2, notes.size.toString())
            }
            else ->
                binding.quantityNotes.text = getString(R.string.note_size3, notes.size.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}