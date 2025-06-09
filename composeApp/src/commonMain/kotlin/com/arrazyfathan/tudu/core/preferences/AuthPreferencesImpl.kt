package com.arrazyfathan.tudu.core.preferences

import PreferencesKeys
import PrefsDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AuthPreferencesImpl(
    private val dataStore: PrefsDataStore, private val ioDispatcher: CoroutineDispatcher
) : AuthPreferences {

    override val accessToken: Flow<String>
        get() = dataStore.data.map { prefs ->
            prefs[PreferencesKeys.accessToken] ?: ""
        }
    override val isAuthenticate: Flow<Boolean>
        get() = accessToken.map { it.isNotBlank() }


    override suspend fun saveAccessToken(token: String) {
        withContext(ioDispatcher) {
            dataStore.edit { prefs ->
                prefs[PreferencesKeys.accessToken] = token
            }
        }
    }
}