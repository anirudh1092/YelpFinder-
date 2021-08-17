package com.example.yelpfinder.models.database

import androidx.room.TypeConverter
import com.example.yelpfinder.models.networkDataSorces.CoordinatesNetworkData
import com.example.yelpfinder.models.networkDataSorces.LocationsNetworkData

class Converters {

    @TypeConverter
    fun stringListToString(list: List<String>?): String {
        if (list == null || list.isEmpty()) {
            return emptyString
        }
        val builder = StringBuilder(list.size * 15)
        for (s in list) {
            builder.append(s).append(",")
        }

        // Trim the trailing comma
        if (builder.isNotEmpty()) {
            builder.replace(builder.length - 1, builder.length, "")
        }

        return builder.toString()
    }

    @TypeConverter
    fun stringToStringList(s: String?): List<String> {
        if (s == null || s.isEmpty()) {
            return listOf()
        }
        return s.split(",")
    }

    @TypeConverter
    fun coordinatesToString(coordinates: CoordinatesNetworkData): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(LATITIUDE)
        stringBuilder.append(coordinates.latitude)
        stringBuilder.append(coordinates_delimiter)
        stringBuilder.append(LONGITUDE)
        stringBuilder.append(coordinates.longitude)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun stringToCoordinates(string: String): CoordinatesNetworkData {
        val latitudeString = string.split(coordinates_delimiter)[0]
        val longitudeString = string.split(coordinates_delimiter)[1]
        val latitude = (latitudeString.split(":")[1]).toDouble()
        val longitude = (longitudeString.split(":")[1]).toDouble()
        return CoordinatesNetworkData(latitude, longitude)
    }


    @TypeConverter
    fun locationToString(location: LocationsNetworkData): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(Address1)
        stringBuilder.append(location.address1)
        stringBuilder.append(location_delimiter)

        stringBuilder.append(Address2)
        stringBuilder.append(location.address2)
        stringBuilder.append(location_delimiter)

        stringBuilder.append(Address3)
        stringBuilder.append(location.address3)
        stringBuilder.append(location_delimiter)

        stringBuilder.append(City)
        stringBuilder.append(location.city)
        stringBuilder.append(location_delimiter)

        stringBuilder.append(Zipcode)
        stringBuilder.append(location.zipCode)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun stringToLocation(string: String): LocationsNetworkData {
        val locations = string.split(location_delimiter)
        val address1 = locations[0].split(":")[1]

        val address2 = locations[1].split(":")[1]

        val address3 = locations[2].split(":")[1]

        val city = locations[3].split(":")[1]

        val zipCode = locations[4].split(":")[1].toInt()
        return LocationsNetworkData(
            address1 = address1,
            address2 = address2,
            address3 = address3,
            city = city,
            zipCode = zipCode
        )
    }

    companion object {
        // Separates the elements of the array list of offline keys in its stringified version
        private const val DELIMITER = "@@KEY@@"
        private const val emptyString = ""
        private const val LATITIUDE = "latitude:"
        private const val LONGITUDE = "longitude:"
        private const val coordinates_delimiter = "@COORD"

        private const val Address1 = "address1:"
        private const val Address2 = "address2:"
        private const val Address3 = "address3:"
        private const val City = "city:"
        private const val Zipcode = "zipCode:"
        private const val location_delimiter = "@LOC"
    }
}