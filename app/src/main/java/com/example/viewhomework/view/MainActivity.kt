package com.example.viewhomework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewhomework.view.fragment.MainFragment
import com.example.viewhomework.R
import com.example.viewhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_view, MainFragment())
        fragmentTransaction.commit()

    }
}