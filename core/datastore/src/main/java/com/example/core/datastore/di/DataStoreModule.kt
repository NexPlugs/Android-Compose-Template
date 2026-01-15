package com.example.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.core.datastore.api.OnboardingDataSource
import com.example.core.datastore.api.TokenDataSource
import com.example.core.datastore.datasource.AppOnboardingDataSource
import com.example.core.datastore.datasource.AppTokenDataSource
import com.example.core.datastore.security.CryptoManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val ONBOARDING_DATASTORE_NAME = "onboarding_datastore"
private const val TOKEN_DATASTORE_NAME = "token_datastore"

private val Context.onboardingDataStore: DataStore<Preferences> by preferencesDataStore(name = ONBOARDING_DATASTORE_NAME)
private val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore(name = TOKEN_DATASTORE_NAME)

@Module
@InstallIn(SingletonComponent::class)
internal interface DataStoreModule {

    @Provides
    @Singleton
    fun provideCryptoManager(): CryptoManager = CryptoManager()


    @OnboardingDataStore
    @Provides
    fun provideOnboardingDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.onboardingDataStore


    @TokenDataStore
    fun provideTokenDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.tokenDataStore


    @Binds
    fun bindOnboardingDataSource(impl: AppOnboardingDataSource): OnboardingDataSource


    @Binds
    fun bindTokenDataSource(impl: AppTokenDataSource): TokenDataSource
}