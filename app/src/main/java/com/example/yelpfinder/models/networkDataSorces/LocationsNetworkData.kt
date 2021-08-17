package com.example.yelpfinder.models.networkDataSorces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class LocationsNetworkData(

    @SerializedName("address1")
    @Expose
    var address1: String,

    @SerializedName("address2")
    @Expose
    var address2: String,

    @SerializedName("address3")
    @Expose
    var address3: String,

    @SerializedName("city")
    @Expose
    var city: String,

    @SerializedName("zip_code")
    @Expose
    var zipCode: Int,
)



