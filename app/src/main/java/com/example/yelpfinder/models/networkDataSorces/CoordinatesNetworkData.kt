package com.example.yelpfinder.models.networkDataSorces

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CoordinatesNetworkData(

    @SerializedName("latitude")
    @Expose
    var latitude : Double,

    @SerializedName("longitude")
    @Expose
    var longitude : Double
)
