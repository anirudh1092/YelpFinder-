package com.example.yelpfinder.di

import com.example.yelpfinder.api.BusinessesApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    val BASE_URL = "https://api.yelp.com/v3/"
    val AUTHORIZATION_HEADER = "Authorization"
    val authorization_token =
        "Bearer 2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx"

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)

        builder.addInterceptor {
            val request =
                it.request().newBuilder().addHeader(AUTHORIZATION_HEADER, authorization_token)
                    .build()
            it.proceed(request)
        }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideBusinessesService(
        retrofit: Retrofit.Builder,
        client: OkHttpClient
    ): BusinessesApiService {
        return retrofit
            .client(client)
            .build()
            .create(BusinessesApiService::class.java)
    }
}
