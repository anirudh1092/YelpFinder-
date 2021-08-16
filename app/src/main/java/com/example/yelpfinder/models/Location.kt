package com.example.yelpfinder.models

data class Location(
    var address1: String,
    var address2 : String,
    var address3 : String,
    var city : String,
    var zipCode : Int,
    var displayAddress : List<String>
)
