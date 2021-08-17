package com.example.yelpfinder.models.dataModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(
    var latitude : Double,
    var longitude : Double
):Parcelable
