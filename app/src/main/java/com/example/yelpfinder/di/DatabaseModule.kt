package com.example.yelpfinder.di

import android.content.Context
import androidx.room.Room
import com.example.yelpfinder.models.database.BusinessEntityDatabase
import com.example.yelpfinder.models.database.businessDataCacheModels.BusinessDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesBusinessDatabase(@ApplicationContext context: Context): BusinessEntityDatabase {
        return Room.databaseBuilder(
            context,
            BusinessEntityDatabase::class.java,
            BusinessEntityDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesBusinessDao(businessEntityDatabase: BusinessEntityDatabase): BusinessDataDao {
        return businessEntityDatabase.businessDataDao()
    }
}
