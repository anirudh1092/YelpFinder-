package com.example.yelpfinder.models.database.businessDataCacheModels

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BusinessDataDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBusinessData(businessDataEntity: BusinessDataEntity)

    @Update
    suspend fun updateBusinessData(businessDataEntity: BusinessDataEntity)

    @Delete
    suspend fun deleteBusinessData(businessDataEntity: BusinessDataEntity)

    @Query("SELECT * from BusinessDataEntity")
    fun getAllBusinesses(): List<BusinessDataEntity>

    @Query("SELECT * from BusinessDataEntity where id like :id ")
    fun getBusinessDataForId(id: String): BusinessDataEntity

}