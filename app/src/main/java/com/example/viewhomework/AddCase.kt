package com.example.viewhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.viewhomework.databinding.AddCaseBinding

class AddCase : Fragment(R.layout.add_case) {

    private var _binding: AddCaseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddCaseBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.closeButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_view, MainFragment())
            fragmentTransaction.commit()
        }

        binding.switchDate.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.calendarLayout.visibility = View.VISIBLE
                binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                    binding.dateEditField.text = "$dayOfMonth.$month.$year"
                }

                binding.textOk.setOnClickListener {
                    binding.calendarLayout.visibility = View.INVISIBLE
                }
            } else {
                binding.calendarLayout.visibility = View.INVISIBLE
                binding.dateEditField.text = null
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}