package com.example.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.core.datastore.api.TokenDataSource
import com.example.core.datastore.di.TokenDataStore
import com.example.core.datastore.security.CryptoManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppTokenDataSource @Inject constructor(
    private val cryptoManager: CryptoManager,
    @TokenDataStore private val dataStore: DataStore<Preferences>
): TokenDataSource {

    companion object {

        /** Preference keys for storing tokens */
        private object TokenPreferencesKeys {
            val ACCESS_TOKEN = stringPreferencesKey("access_token")
            val REFRESH_TOKEN = stringPreferencesKey("refresh")
        }
    }

    override val accessToken: Flow<String>
        get() = dataStore.data
            .map { prefs ->
                val encryptedToken = prefs[TokenPreferencesKeys.ACCESS_TOKEN] ?: ""
                cryptoManager.decrypt(encryptedToken)
            }.catch { throw it }

    override val refreshToken: Flow<String>
        get() = dataStore.data
            .map { prefs ->
                val encryptedToken = prefs[TokenPreferencesKeys.REFRESH_TOKEN] ?: ""
                cryptoManager.decrypt(encryptedToken)
            }.catch { throw it }

    override suspend fun getAccessToken(): String = accessToken.first()

    override suspend fun getRefreshToken(): String = refreshToken.first()

    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        try {
            dataStore.edit {
                val encryptedAccessToken = cryptoManager.encrypt(accessToken)
                val encryptedRefreshToken = cryptoManager.encrypt(refreshToken)

                it[TokenPreferencesKeys.ACCESS_TOKEN] = encryptedAccessToken
                it[TokenPreferencesKeys.REFRESH_TOKEN] = encryptedRefreshToken
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        try {
            dataStore.edit {
                val encryptedRefreshToken = cryptoManager.encrypt(refreshToken)
                it[TokenPreferencesKeys.REFRESH_TOKEN] = encryptedRefreshToken
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun setAccessToken(accessToken: String) {
        try {
            dataStore.edit {
                val encryptedAccessToken = cryptoManager.encrypt(accessToken)
                it[TokenPreferencesKeys.ACCESS_TOKEN] = encryptedAccessToken
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun clearTokens() {
        try {
            dataStore.edit {
                it.remove(TokenPreferencesKeys.ACCESS_TOKEN)
                it.remove(TokenPreferencesKeys.REFRESH_TOKEN)
            }
        } catch (e: Exception) {
            throw e
        }
    }
}