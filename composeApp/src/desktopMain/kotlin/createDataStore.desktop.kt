import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

val prefs = createDataStore { DATA_STORE_FILE_NAME }

@Composable
actual fun rememberDataStore(): PrefsDataStore {
    return remember { prefs }
}
