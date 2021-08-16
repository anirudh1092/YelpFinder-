package com.example.yelpfinder.models

data class BusinessDataModel(
    var id: String,
    var name: String,
    var imageUrl: String,
    var isClosed: Boolean,
    val businessUrl: String,
    var reviewCount: Int,
    var ratings: Double,
    var coordinates: Coordinates,
    var location: Location,
    var phone: String,
    var distance: Long
)
