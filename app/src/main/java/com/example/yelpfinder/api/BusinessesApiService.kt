package com.example.yelpfinder.api

import com.example.yelpfinder.models.networkDataSorces.BusinessesNetworkDataSource
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BusinessesApiService {
    @Headers("Authorization: Bearer 2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx")
    @GET("businesses/search")
    suspend fun getBusinessData(@Query("term") term: String, @Query("location") location: String): BusinessesNetworkDataSource
}