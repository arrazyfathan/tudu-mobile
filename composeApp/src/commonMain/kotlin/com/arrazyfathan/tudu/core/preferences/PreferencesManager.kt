package com.arrazyfathan.tudu.core.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesManager {
    val isFirstTime: Flow<Boolean>

    suspend fun setIsFirstTime(value: Boolean)
}
