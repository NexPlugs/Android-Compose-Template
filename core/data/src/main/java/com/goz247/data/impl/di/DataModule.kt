package com.goz247.data.impl.di

import com.goz247.data.api.AuthRepository
import com.goz247.data.impl.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger module for binding data layer implementations
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}