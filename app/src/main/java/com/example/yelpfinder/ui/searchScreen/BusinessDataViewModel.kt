package com.example.yelpfinder.ui.searchScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yelpfinder.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessDataViewModel
@Inject constructor(
    private val repository: BusinessDataRepository
) : ViewModel() {

    fun getData(){
        viewModelScope.launch {
           repository.getBusinessApiData().collect {
               when (it) {
                   is DataState.Success -> {
                       val businesses   = it.data
                       Log.d("data : ", businesses.toString())

                   }
                   is DataState.Error -> {
                       Log.e("data", it.exception.localizedMessage)
                   }
                   is DataState.Loading -> {

                   }
               }
           }

        }
    }


}