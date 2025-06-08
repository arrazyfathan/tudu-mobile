package com.arrazyfathan.tudu.core.preferences

import PreferencesKeys
import PrefsDataStore
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject

class PreferencesManagerImpl(
    private val dataStore: PrefsDataStore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PreferencesManager {

    override val isFirstTime: Flow<Boolean>
        get() = dataStore.data.map { prefs ->
            prefs[PreferencesKeys.isFirstTimeKeys] ?: true
        }

    override suspend fun setIsFirstTime(value: Boolean) {
        withContext(ioDispatcher) {
            dataStore.edit { prefs ->
                prefs[PreferencesKeys.isFirstTimeKeys] = value
            }
        }
    }
}

@Composable
fun rememberPreferenceManager(): PreferencesManager {
    return koinInject()
}