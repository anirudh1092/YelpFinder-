package com.example.yelpfinder.ui.searchScreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.yelpfinder.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: BusinessDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            val term = "pizza"
            val location = "111%20Sutter%20St,%20San%20Francisco,%20"
            viewModel.getData(term, location)
        }
        setContentView(binding.root)
    }
}
