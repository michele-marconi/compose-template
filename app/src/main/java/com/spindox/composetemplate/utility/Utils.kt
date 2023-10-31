package com.spindox.composetemplate.utility

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.spindox.composetemplate.enums.ThemeAppearance

@Composable
fun isDarkTheme(themeAppearance: ThemeAppearance): Boolean {
    return when (themeAppearance) {
        ThemeAppearance.AUTO -> isSystemInDarkTheme()
        ThemeAppearance.LIGHT -> false
        ThemeAppearance.DARK -> true
    }
}
