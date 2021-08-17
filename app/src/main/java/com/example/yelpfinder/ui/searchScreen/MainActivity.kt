package com.example.yelpfinder.ui.searchScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.yelpfinder.R
import com.example.yelpfinder.databinding.ActivityMainBinding
import com.example.yelpfinder.ui.businesseScreen.FragmentBusinesses
import com.example.yelpfinder.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: BusinessDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        var term: String? = null
        var location: String? = null

        binding.mainScreenTermEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.button.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()
                term = s.toString().trim()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })

        binding.mainScreenLocationEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.button.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()
                location = s.toString().trim()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })

        binding.button.setOnClickListener {
            term?.let {
                location?.let { it1 ->
                    viewModel.setStateEvent(
                        BusinessDataViewModel.MainStateEvent.SearchClicked(
                            it,
                            it1
                        )
                    )
                }
            }
        }

        viewModel.dataState.observe(this, Observer {
            when (it) {
                is DataState.Success -> {
                    val fragment = FragmentBusinesses.getInstance(this, it.data)
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_container, fragment)
                    transaction.commit()
                }
                is DataState.Error -> {
                    Log.e("MainActivity", it.exception.toString())
                }
            }
        })

        setContentView(binding.root)
    }
}
