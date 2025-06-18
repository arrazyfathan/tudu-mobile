package com.arrazyfathan.tudu.core.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesManager {
    val isOnboarded: Flow<Boolean>

    suspend fun setIsOnboarded(value: Boolean)
}
