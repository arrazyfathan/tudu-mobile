package com.arrazyfathan.tudu.core.preferences

import com.arrazyfathan.tudu.core.domain.auth.AuthInfo

interface AuthPreferences {
    suspend fun isAuthenticated(): Boolean

    suspend fun getAuthInfo(): AuthInfo?

    suspend fun save(authInfo: AuthInfo)

    suspend fun clear()
}
