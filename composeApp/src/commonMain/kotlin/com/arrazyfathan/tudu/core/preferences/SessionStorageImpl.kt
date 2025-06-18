package com.arrazyfathan.tudu.core.preferences

import PreferencesKeys
import PrefsDataStore
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.edit
import com.arrazyfathan.tudu.core.domain.model.AuthInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject

class SessionStorageImpl(
    private val dataStore: PrefsDataStore,
    private val ioDispatcher: CoroutineDispatcher,
) : SessionStorage {
    override fun isAuthenticated(): Flow<Boolean> = getAuthInfo().map { !it?.accessToken.isNullOrBlank() }

    override fun getAuthInfo(): Flow<AuthInfo?> =
        dataStore.data.map { data ->
            val accessToken = data[PreferencesKeys.accessToken]
            val refreshToken = data[PreferencesKeys.refreshToken]
            val userId = data[PreferencesKeys.userId]
            if (accessToken != null && refreshToken != null && userId != null) {
                AuthInfo(accessToken, refreshToken, userId)
            } else {
                null
            }
        }

    override suspend fun save(authInfo: AuthInfo) {
        withContext(ioDispatcher) {
            dataStore.edit { prefs ->
                prefs[PreferencesKeys.accessToken] = authInfo.accessToken.orEmpty()
                prefs[PreferencesKeys.refreshToken] = authInfo.refreshToken.orEmpty()
                prefs[PreferencesKeys.userId] = authInfo.userId.orEmpty()
            }
        }
    }

    override suspend fun clear() {
        dataStore.edit { prefs ->
            prefs.remove(PreferencesKeys.accessToken)
            prefs.remove(PreferencesKeys.refreshToken)
            prefs.remove(PreferencesKeys.userId)
        }
    }
}

@Composable
fun rememberAuthPreference(): SessionStorage = koinInject()
