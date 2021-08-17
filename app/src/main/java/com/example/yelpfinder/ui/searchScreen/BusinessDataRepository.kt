package com.example.yelpfinder.ui.searchScreen

import androidx.room.ColumnInfo
import com.example.yelpfinder.api.BusinessesApiService
import com.example.yelpfinder.models.database.businessDataCacheModels.BusinessDataDao
import com.example.yelpfinder.models.database.businessDataCacheModels.BusinessDataEntity
import com.example.yelpfinder.models.networkDataSorces.BusinessDataNetworkDataSource
import com.example.yelpfinder.models.networkDataSorces.CoordinatesNetworkData
import com.example.yelpfinder.models.networkDataSorces.LocationsNetworkData
import com.example.yelpfinder.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BusinessDataRepository
@Inject constructor(
    private val retrofit: BusinessesApiService,
    private val businessDataDao: BusinessDataDao
) {

    suspend fun getBusinessApiData(
        term: String,
        location: String
    ): Flow<DataState<BusinessDataEntity>> =
        flow {
            try {
                val data =
                    retrofit.getBusinessData(
                        term,
                        location
                    ).businesses
                for (business in data) {
                    businessDataDao.saveBusinessData(
                        businessDataEntity = generateBusinessDataEntity(business)
                    )
                }
                val cache = businessDataDao.getAllBusinesses()
                for (business in cache) {
                    emit(DataState.Success(business))
                }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

    private fun generateBusinessDataEntity(business: BusinessDataNetworkDataSource): BusinessDataEntity {
        return BusinessDataEntity(
            id = business.id,
            name = business.name,
            imageUrl = business.imageUrl,
            isClosed = business.isClosed,
            businessUrl = business.businessUrl,
            reviewCount = business.reviewCount,
            ratings = business.ratings,
            coordinates = business.coordinates,
            location = business.location,
            phone = business.phone,
            distance = business.distance
        )
    }
}

