package com.arrazyfathan.tudu.core.preferences

import kotlinx.coroutines.flow.Flow

interface AuthPreferences {
    val accessToken: Flow<String>
    val isAuthenticate: Flow<Boolean>
    suspend fun saveAccessToken(token: String)
}