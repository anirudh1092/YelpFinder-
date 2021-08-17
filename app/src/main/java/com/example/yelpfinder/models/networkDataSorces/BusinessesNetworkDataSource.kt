package com.example.yelpfinder.models.networkDataSorces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BusinessesNetworkDataSource(

    @SerializedName("businesses")
    @Expose
    var businesses : List<BusinessDataNetworkDataSource>,
    @SerializedName("total")
    @Expose
    var total : Int
)
