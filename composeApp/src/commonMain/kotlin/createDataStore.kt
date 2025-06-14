import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

typealias PrefsDataStore = DataStore<Preferences>

fun createDataStore(producePath: () -> String): PrefsDataStore {
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() },
    )
}

internal const val DATA_STORE_FILE_NAME = "tudu.preferences_pb"

@Composable
expect fun rememberDataStore(): PrefsDataStore
