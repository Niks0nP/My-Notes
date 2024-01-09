package com.example.viewhomework.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewhomework.view.adapter.MyRecyclerAdapter
import com.example.viewhomework.R
import com.example.viewhomework.data.repository.TodoItemsRepository
import com.example.viewhomework.databinding.MainScreenBinding
import com.example.viewhomework.view.App

class MainFragment : Fragment() {

    private var _binding: MainScreenBinding? = null
    private val binding get() = _binding!!
    private val todoItemsRepository: TodoItemsRepository?
        get() = (activity?.applicationContext as? App)?.todoItemsRepository

    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

//    private val toDoList : List<String> = arrayListOf(
//        "Сделать домашнее задание",
//        "Покормить кота",
//        "Убрать в комнате",
//        "Настя леди баг")

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

        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myRecyclerAdapter.data = todoItemsRepository?.getList() ?: emptyList()
        binding.myRecyclerView.adapter = myRecyclerAdapter
    }

    override fun onResume() {
        super.onResume()
        myRecyclerAdapter.data = todoItemsRepository?.getList() ?: emptyList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}