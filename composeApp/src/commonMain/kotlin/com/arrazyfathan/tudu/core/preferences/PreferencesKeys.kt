import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val isFirstTimeKeys = booleanPreferencesKey("first_time_key")
    val accessToken = stringPreferencesKey("access_token_key")
    val refreshToken = stringPreferencesKey("refresh_token_key")
    val userId = stringPreferencesKey("user_id_key")
}
