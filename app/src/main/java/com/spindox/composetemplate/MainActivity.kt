package com.spindox.composetemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.spindox.composetemplate.data.datastore.DataStoreManager
import com.spindox.composetemplate.ui.App
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(dataStoreManager = DataStoreManager(this))
        }
    }
}
