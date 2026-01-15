package com.goz247.database.di

import android.app.Application
import androidx.room.Room
import com.goz247.database.AppDatabase
import com.goz247.database.domain.user.UserDao
import com.goz247.database.domain.user.UserRoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "app_database") // TODO: Edit database name
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()


    /**
     * Provides Repository implementations
     */

    @IODatabaseDispatcher
    @Provides
    fun provideIODatabaseDispatcher() = CoroutineScope(Dispatchers.IO)


    @Provides
    @Singleton
    fun provideUserRoomRepository(
        userDao: UserDao,
        @IODatabaseDispatcher ioDatabaseDispatcher: CoroutineScope
    ) = UserRoomRepository( userDao = userDao, scope = ioDatabaseDispatcher)
}