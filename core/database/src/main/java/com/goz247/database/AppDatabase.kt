package com.goz247.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.goz247.database.domain.user.UserDao
import com.goz247.database.domain.user.UserEntity

@Database(
    entities = [ UserEntity::class ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}