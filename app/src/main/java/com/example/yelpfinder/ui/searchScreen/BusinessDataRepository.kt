package com.example.yelpfinder.ui.searchScreen

import com.example.yelpfinder.api.BusinessesApiService
import com.example.yelpfinder.models.networkDataSorces.BusinessDataNetworkDataSource
import com.example.yelpfinder.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BusinessDataRepository
@Inject constructor(
    private val retrofit: BusinessesApiService
) {

    suspend fun getBusinessApiData(): Flow<DataState<BusinessDataNetworkDataSource>> =
        flow {
            try {
                val data =
                    retrofit.getBusinessData("beer", "111%20Sutter%20St,%20San%20Francisco,%20").businesses


                for(business in data) {
                    emit(DataState.Success(business))
                }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
}

