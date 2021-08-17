package com.example.yelpfinder.ui.searchScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yelpfinder.models.dataModels.BusinessesModel
import com.example.yelpfinder.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessDataViewModel
@Inject constructor(
    private val repository: BusinessDataRepository
) : ViewModel() {

    val dataState: MutableLiveData<DataState<BusinessesModel>> = MutableLiveData()

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (mainStateEvent) {
                is MainStateEvent.SearchClicked -> {
                    getData(mainStateEvent.term, mainStateEvent.location)
                }
                is MainStateEvent.DataLoaded -> {
                    dataState.postValue(DataState.Success(mainStateEvent.data))
                }
                is MainStateEvent.ErrorFetching -> {
                    dataState.postValue(DataState.Error(mainStateEvent.exception))
                }
            }
        }
    }


    suspend fun getData(term: String, location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBusinessApiData(term, location).collect {
                when (it) {
                    is DataState.Success -> {
                        setStateEvent(MainStateEvent.DataLoaded(it.data))
                    }
                    is DataState.Error -> {
                        setStateEvent(MainStateEvent.ErrorFetching(it.exception))
                    }
                }
            }
        }
    }

    sealed class MainStateEvent {
        data class SearchClicked(val term: String, val location: String) : MainStateEvent()
        data class DataLoaded(val data: BusinessesModel) : MainStateEvent()
        data class ErrorFetching(val exception: Exception) : MainStateEvent()
    }
}
