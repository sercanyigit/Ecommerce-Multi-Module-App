package com.sercan.ecommerce.ui.theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.sercan.ecommerce.common.datastore.PreferenceKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemeManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    val isDarkMode: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[PreferenceKeys.IS_DARK_MODE] ?: false
        }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.IS_DARK_MODE] = isDarkMode
        }
    }
}