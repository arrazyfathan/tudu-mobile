package com.arrazyfathan.tudu.core.preferences

import com.arrazyfathan.tudu.core.domain.model.AuthInfo
import kotlinx.coroutines.flow.Flow

interface SessionStorage {
    fun isAuthenticated(): Flow<Boolean>

    fun getAuthInfo(): Flow<AuthInfo?>

    suspend fun save(authInfo: AuthInfo)

    suspend fun clear()
}
