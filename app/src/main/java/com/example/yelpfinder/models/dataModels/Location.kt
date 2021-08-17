package com.example.yelpfinder.models.dataModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    var address1: String,
    var address2: String,
    var address3: String,
    var city: String,
    var zipCode: Int
) : Parcelable
