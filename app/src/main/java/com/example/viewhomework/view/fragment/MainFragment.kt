package com.example.viewhomework.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewhomework.view.adapter.MyRecyclerAdapter
import com.example.viewhomework.R
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
        myRecyclerAdapter = MyRecyclerAdapter { case ->
            val frag = ChangeFragment().apply {
                arguments = bundleOf("caseId" to case.id)
            }
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_view, frag)
                .commit()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_view, AddCaseFragment())
                .commit()
        }

        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.myRecyclerView.adapter = myRecyclerAdapter

        nNotesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        nNotesViewModel.readAllData.observe(viewLifecycleOwner, Observer {notes ->
            myRecyclerAdapter.setData(notes)
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}