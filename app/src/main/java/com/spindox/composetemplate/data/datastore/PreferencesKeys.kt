package com.spindox.composetemplate.data.datastore

import androidx.datastore.preferences.core.intPreferencesKey

object PreferencesKeys {
    const val APP_PREFERENCES = "app_preferences"
    val THEME_APPEARANCE = intPreferencesKey("THEME_APPEARANCE")
}