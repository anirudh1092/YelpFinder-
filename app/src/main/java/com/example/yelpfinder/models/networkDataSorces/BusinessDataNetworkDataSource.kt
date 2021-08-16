package com.example.yelpfinder.models.networkDataSorces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BusinessDataNetworkDataSource(

    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("image_url")
    @Expose
    var imageUrl: String,

    @SerializedName("is_closed")
    @Expose
    var isClosed: Boolean,

    @SerializedName("url")
    @Expose
    val businessUrl: String,

    @SerializedName("review_count")
    @Expose
    var reviewCount: Int,

    @SerializedName("rating")
    @Expose
    var ratings: Double,

    @SerializedName("coordinates")
    @Expose
    var coordinates: CoordinatesNetworkData,

    @SerializedName("location")
    @Expose
    var location: LocationsNetworkData,

    @SerializedName("phone")
    @Expose
    var phone: String,

    @SerializedName("distance")
    @Expose
    var distance: String
)
