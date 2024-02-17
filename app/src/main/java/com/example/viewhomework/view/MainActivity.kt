package com.example.viewhomework.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.navigation.findNavController
import com.example.viewhomework.view.fragment.MainFragment
import com.example.viewhomework.R
import com.example.viewhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT))
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragment_view, MainFragment())
//            .commit()
    }
}