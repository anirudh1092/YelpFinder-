package com.example.yelpfinder.ui.searchScreen

import com.example.yelpfinder.api.BusinessesApiService
import com.example.yelpfinder.models.database.businessDataCacheModels.BusinessDataDao
import com.example.yelpfinder.models.database.businessDataCacheModels.BusinessDataEntity
import com.example.yelpfinder.models.networkDataSorces.BusinessDataNetworkDataSource
import com.example.yelpfinder.models.networkDataSorces.BusinessesNetworkDataSource
import com.example.yelpfinder.util.DataState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit

class BusinessDataRepositoryTest : TestCase() {

    @MockK
    lateinit var mockRetrofit: BusinessesApiService

    @MockK
    lateinit var mockDao: BusinessDataDao

    @MockK
    lateinit var mockDataEntity: BusinessDataEntity

    @MockK
    lateinit var mockBusinessesNetworkDataSource: BusinessesNetworkDataSource

    @MockK
    lateinit var mockBusinesses : List<BusinessDataNetworkDataSource>

    lateinit var businessDataRepository: BusinessDataRepository

    val FAKE_TERM = "pizza"

    val FAKE_LOCATION = "San Francisco"

    override fun setUp() {

        MockKAnnotations.init(this)
        super.setUp()

        businessDataRepository = BusinessDataRepository(
            mockRetrofit,
            mockDao
        )


    }

    fun testgetBusinessData() {
//        runBlocking {
//            coEvery { mockRetrofit.getBusinessData(FAKE_TERM,FAKE_LOCATION).businesses }.returns(mockBusinesses)
//            coEvery { mockRetrofit.getBusinessData(FAKE_TERM, FAKE_LOCATION) }.returns(
//                mockBusinessesNetworkDataSource
//            )
//            coEvery { mockBusinessesNetworkDataSource.businesses }.returns(mockBusinesses)
//            val data = businessDataRepository.getBusinessData(FAKE_TERM, FAKE_LOCATION)
//            data.collect {
//                assert(it is DataState.Success)
//            }
//
//
//        }
    }

    override fun tearDown() {
        super.tearDown()
    }
}