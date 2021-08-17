package com.example.yelpfinder.models.database.businessDataCacheModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yelpfinder.models.networkDataSorces.CoordinatesNetworkData
import com.example.yelpfinder.models.networkDataSorces.LocationsNetworkData

@Entity(tableName = "BusinessDataEntity")
data class BusinessDataEntity(
    @PrimaryKey
    @ColumnInfo
    var id: String,

    @ColumnInfo
    var name: String,

    @ColumnInfo
    var imageUrl: String,


    @ColumnInfo
    var isClosed: Boolean,

    @ColumnInfo
    val businessUrl: String,

    @ColumnInfo
    var reviewCount: Int,

    @ColumnInfo
    var ratings: Double,

    @ColumnInfo
    var coordinates: CoordinatesNetworkData,

    @ColumnInfo
    var location: LocationsNetworkData,

    @ColumnInfo
    var phone: String,

    @ColumnInfo
    var distance: String

)

