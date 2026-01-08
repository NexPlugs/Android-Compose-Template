package com.example.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.MediaType.Companion.toMediaType
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                //TODO: Add log interceptor, timeouts, token, etc.
            }
            .build()
    }


    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): retrofit2.Retrofit {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://api.example.com/") // Replace with actual base URL
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".t()))
            .build()
    }


}