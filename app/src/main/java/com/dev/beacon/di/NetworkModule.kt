package com.dev.beacon.di

import com.dev.beacon.data.remote.api.BeaconApi
import com.dev.beacon.data.repository.BeaconRepository
import com.dev.beacon.data.repository.BeaconRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://your.api.base.url/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBeaconApi(retrofit: Retrofit): BeaconApi {
        return retrofit.create(BeaconApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBeaconRepository(api: BeaconApi): BeaconRepository {
        return BeaconRepositoryImpl(api)
    }
}
