package com.example.viewhomework.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    ): View? {
        _binding = MainScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        myRecyclerAdapter = MyRecyclerAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_view, AddCaseFragment())
            fragmentTransaction.commit()
        }

        val adapter = MyRecyclerAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.my_recycler_view)
        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.myRecyclerView.adapter = myRecyclerAdapter
        recyclerView.adapter = adapter

        nNotesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        nNotesViewModel.readAllData.observe(viewLifecycleOwner, Observer {notes ->
            adapter.setData(notes)
        })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}