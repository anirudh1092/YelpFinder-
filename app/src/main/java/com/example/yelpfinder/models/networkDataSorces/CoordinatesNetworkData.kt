package com.example.yelpfinder.models.networkDataSorces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoordinatesNetworkData(

    @SerializedName("latitude")
    @Expose
    var latitude : Double,

    @SerializedName("longitude")
    @Expose
    var longitude : Double
)
