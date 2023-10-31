package com.spindox.composetemplate.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.spindox.composetemplate.data.datastore.PreferencesKeys.THEME_APPEARANCE
import com.spindox.composetemplate.enums.ThemeAppearance
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

val Context.dataStore by preferencesDataStore(PreferencesKeys.APP_PREFERENCES)

class DataStoreManager @Inject constructor(@ApplicationContext context: Context) {
    private val settingsDataStore = context.dataStore
    private val coroutineScope = CoroutineScope(Job())

    suspend fun resetDataStore() {
        settingsDataStore.edit {
            it.clear()
        }
    }

    suspend fun setThemeAppearance(theme: ThemeAppearance) {
        settingsDataStore.edit { settings ->
            settings[THEME_APPEARANCE] = theme.value
        }
    }

    val themeAppearance: StateFlow<ThemeAppearance> = settingsDataStore.data.map { preferences ->
        ThemeAppearance.getByValue(preferences[THEME_APPEARANCE])
    }.stateIn(coroutineScope, SharingStarted.Eagerly, ThemeAppearance.AUTO)

}