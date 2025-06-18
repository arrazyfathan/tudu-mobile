package com.arrazyfathan.tudu.core.preferences

import PreferencesKeys
import PrefsDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PreferencesManagerImpl(
    private val dataStore: PrefsDataStore,
    private val ioDispatcher: CoroutineDispatcher,
) : PreferencesManager {
    override val isOnboarded: Flow<Boolean>
        get() =
            dataStore.data.map { prefs ->
                prefs[PreferencesKeys.isOnboarded] ?: false
            }

    override suspend fun setIsOnboarded(value: Boolean) {
        withContext(ioDispatcher) {
            dataStore.edit { prefs ->
                prefs[PreferencesKeys.isOnboarded] = value
            }
        }
    }
}
