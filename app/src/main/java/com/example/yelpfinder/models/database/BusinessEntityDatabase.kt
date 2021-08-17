package com.example.yelpfinder.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.yelpfinder.models.database.businessDataCacheModels.BusinessDataDao
import com.example.yelpfinder.models.database.businessDataCacheModels.BusinessDataEntity

@Database(entities = [BusinessDataEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class BusinessEntityDatabase() : RoomDatabase() {
    abstract fun businessDataDao(): BusinessDataDao

    companion object {
        val DATABASE_NAME = "business_data_database"
    }
}