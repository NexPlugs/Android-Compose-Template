package com.example.core.datastore.api

import com.example.core.model.OnboardingState
import kotlinx.coroutines.flow.Flow

interface OnboardingDataSource {
    val onboardingState: Flow<OnboardingState>
    suspend fun setOnboardingCompleted(isCompleted: Boolean)
}