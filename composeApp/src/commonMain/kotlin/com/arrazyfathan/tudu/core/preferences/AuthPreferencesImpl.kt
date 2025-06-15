package com.arrazyfathan.tudu.core.preferences

import PreferencesKeys
import PrefsDataStore
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.edit
import com.arrazyfathan.tudu.core.domain.auth.AuthInfo
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject

class AuthPreferencesImpl(
    private val dataStore: PrefsDataStore,
    private val ioDispatcher: CoroutineDispatcher,
) : AuthPreferences {
    override suspend fun isAuthenticated(): Boolean {
        val token = getAuthInfo()?.accessToken
        return !token.isNullOrBlank()
    }

    override suspend fun getAuthInfo(): AuthInfo? {
        val data = dataStore.data.first()
        val accessToken = data[PreferencesKeys.accessToken] ?: return null
        val refreshToken = data[PreferencesKeys.refreshToken] ?: return null
        val userId = data[PreferencesKeys.userId] ?: return null
        return AuthInfo(accessToken, refreshToken, userId)
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
        Napier.d("AuthPreferencesImpl::clear")
    }
}

@Composable
fun rememberAuthPreference(): AuthPreferences = koinInject()
