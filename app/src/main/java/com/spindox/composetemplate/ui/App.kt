package com.spindox.composetemplate.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.spindox.composetemplate.data.datastore.DataStoreManager
import com.spindox.composetemplate.enums.ThemeAppearance
import com.spindox.composetemplate.ui.navigation.AppNavigation
import com.spindox.composetemplate.ui.theme.AppTheme
import com.spindox.composetemplate.utility.isDarkTheme

@Composable
fun App(dataStoreManager: DataStoreManager) {
    val themeAppearance by dataStoreManager.themeAppearance.collectAsState((ThemeAppearance.AUTO))

    AppTheme(isDarkTheme(themeAppearance)) {
        val navController = rememberNavController()
        Scaffold { innerPadding ->
            AppNavigation(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}
