package com.example.yelpfinder.api

import com.example.yelpfinder.models.networkDataSorces.BusinessesNetworkDataSource
import retrofit2.http.GET
import retrofit2.http.Query

interface BusinessesApiService {

    @GET("businesses/search")
    suspend fun getBusinessData(
        @Query("term") term: String,
        @Query("location") location: String
    ): BusinessesNetworkDataSource
}
