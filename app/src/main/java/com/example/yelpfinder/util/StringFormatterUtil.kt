package com.example.yelpfinder.util

import androidx.compose.ui.text.toLowerCase
import java.util.*

class StringFormatterUtil {

    companion object{
        fun getLocationAndTermString(term: String, location: String): String {
            return term.replace("\\s+", "").trim().lowercase(Locale.ENGLISH) +
                    location.replace("\\s+", "").trim().lowercase(Locale.ENGLISH)
        }
    }

}