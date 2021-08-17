package com.example.yelpfinder.models.dataModels

import android.os.Parcelable
import com.example.yelpfinder.models.database.businessDataCacheModels.BusinessDataEntity
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class BusinessesModel(
    var businesses: List<BusinessDataModel>,
    var total: Int
) : Parcelable
