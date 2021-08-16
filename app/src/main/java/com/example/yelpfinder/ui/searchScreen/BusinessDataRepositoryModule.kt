package com.example.yelpfinder.ui.searchScreen

import com.example.yelpfinder.api.BusinessesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BusinessDataRepositoryModule {

    @Singleton
    @Provides
    fun providesBusinessRepository(retorfit: BusinessesApiService): BusinessDataRepository{
        return BusinessDataRepository(retorfit)
    }

}