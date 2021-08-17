package com.example.yelpfinder.ui.searchScreen

import androidx.room.ColumnInfo
import com.example.yelpfinder.api.BusinessesApiService
import com.example.yelpfinder.models.dataModels.BusinessDataModel
import com.example.yelpfinder.models.dataModels.BusinessesModel
import com.example.yelpfinder.models.dataModels.Coordinates
import com.example.yelpfinder.models.dataModels.Location
import com.example.yelpfinder.models.database.businessDataCacheModels.BusinessDataDao
import com.example.yelpfinder.models.database.businessDataCacheModels.BusinessDataEntity
import com.example.yelpfinder.models.networkDataSorces.BusinessDataNetworkDataSource
import com.example.yelpfinder.models.networkDataSorces.BusinessesNetworkDataSource
import com.example.yelpfinder.models.networkDataSorces.CoordinatesNetworkData
import com.example.yelpfinder.models.networkDataSorces.LocationsNetworkData
import com.example.yelpfinder.util.DataState
import com.example.yelpfinder.util.StringFormatterUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BusinessDataRepository
@Inject constructor(
    private val retrofit: BusinessesApiService,
    private val businessDataDao: BusinessDataDao
) {

    suspend fun getBusinessData(
        term: String,
        location: String,
        cacheMap: Map<String, List<String>>
    ): Flow<DataState<BusinessesModel>> =
        flow {
            try {
                val dataCache = mutableListOf<BusinessDataEntity>()
                val apiParams = StringFormatterUtil.getLocationAndTermString(term, location)
                if (cacheMap.containsKey(apiParams)) {
                    cacheMap.forEach {
                        it.value.forEach { id ->
                            dataCache.add(businessDataDao.getBusinessDataForId(id))
                        }
                    }
                    emit(DataState.Success(generateBusinessesModel(dataCache)))
                } else {
                    val businessDatasource =
                        retrofit.getBusinessData(
                            term,
                            location
                        ).businesses
                    for (business in businessDatasource) {
                        businessDataDao.saveBusinessData(
                            businessDataEntity = generateBusinessDataEntity(business)
                        )
                    }
                    val cache = mutableListOf<BusinessDataEntity>()
                    for (business in businessDatasource) {
                        cache.add(businessDataDao.getBusinessDataForId(business.id))
                    }
                    emit(DataState.Success(generateBusinessesModel(cache)))
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

    private fun generateBusinessesModel(
        cache: List<BusinessDataEntity>
    ): BusinessesModel {
        val businessesModelData = mutableListOf<BusinessDataModel>()

        for (data in cache) {
            val dataModelElement = BusinessDataModel(
                id = data.id,
                name = data.name,
                imageUrl = data.imageUrl,
                isClosed = data.isClosed,
                businessUrl = data.businessUrl,
                reviewCount = data.reviewCount,
                ratings = data.ratings,
                coordinates = generateCoordinate(data.coordinates),
                location = generateLocation(data.location),
                phone = data.phone,
                distance = data.distance
            )
            businessesModelData.add(dataModelElement)
        }
        return BusinessesModel(businessesModelData)
    }

    private fun generateCoordinate(coordinatesNetworkData: CoordinatesNetworkData): Coordinates {
        return Coordinates(
            latitude = coordinatesNetworkData.latitude,
            longitude = coordinatesNetworkData.longitude
        )
    }

    private fun generateLocation(locationsNetworkData: LocationsNetworkData): Location {
        return Location(
            address1 = locationsNetworkData.address1,
            address2 = locationsNetworkData.address2,
            address3 = locationsNetworkData.address3,
            city = locationsNetworkData.city,
            zipCode = locationsNetworkData.zipCode
        )
    }
}

