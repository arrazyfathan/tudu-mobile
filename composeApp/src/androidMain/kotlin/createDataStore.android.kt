import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDataStore(context: Context): DataStore<Preferences> =
    createDataStore(
        producePath = { context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath },
    )

@Composable
actual fun rememberDataStore(): PrefsDataStore {
    val context = LocalContext.current
    return remember { createDataStore(context) }
}
