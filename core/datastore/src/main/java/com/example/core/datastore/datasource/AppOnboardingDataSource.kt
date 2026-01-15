package com.example.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.core.datastore.api.OnboardingDataSource
import com.example.core.datastore.di.OnboardingDataStore
import com.example.core.model.OnboardingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class AppOnboardingDataSource(
    @OnboardingDataStore private val dataStore: DataStore<Preferences>
): OnboardingDataSource {
    companion object {
        private object OnboardingPreferencesKeys {
            val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        }
    }

    override val onboardingState: Flow<OnboardingState>
        get() = dataStore.data
            .map { prefs ->
                val isCompleted = prefs[OnboardingPreferencesKeys.ONBOARDING_COMPLETED] == true
                when(isCompleted) {
                    true -> OnboardingState.COMPLETED
                    false -> OnboardingState.NOT_COMPLETED
                }
            }.catch { exception -> throw exception }

    override suspend fun setOnboardingCompleted(isCompleted: Boolean) {
        dataStore.edit {
            it[OnboardingPreferencesKeys.ONBOARDING_COMPLETED] = isCompleted
        }
    }
}