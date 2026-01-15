package com.goz247.database.domain.user

import androidx.collection.LruCache
import com.example.core.common.extensions.launchWithMutex
import com.example.core.model.UserModel
import com.goz247.database.utils.DEFAULT_CACHE_SIZE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex


class UserRoomRepository(
    private val userDao: UserDao,
    private val scope: CoroutineScope,
    cacheSize: Int = DEFAULT_CACHE_SIZE
){

    private val userCache = LruCache<String, UserEntity>(cacheSize)
    private val mutex = Mutex()



    fun getUser(): UserModel {
        return userCache.snapshot().values.firstOrNull()?.toModel() ?: UserModel.default
    }

    private fun cacheUser(listUser: List<UserEntity>) {
        listUser.forEach { user ->
            userCache.put(user.id, user)
        }
    }


    /**
     * Insert list of user to database and cache
     * Only insert user that not exist in cache
     */
    fun insertListUser(listUser: Collection<UserModel>) {
        if(listUser.isEmpty()) return
        val userToInsert = listUser.filter { user ->
            val cacheUser = userCache[user.id]
            if(cacheUser != null) {
                val isSame = cacheUser.id == user.id
                return@filter !isSame
            }
            return@filter false
        }
        if(userToInsert.isEmpty()) return
        val entities = userToInsert.map { it.toEntity() }
        cacheUser(entities)
        scope.launchWithMutex(mutex) {
            userDao.insertUser(entities)
        }
    }
}