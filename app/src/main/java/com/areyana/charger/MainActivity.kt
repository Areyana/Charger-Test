package com.areyana.charger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.core.view.WindowCompat
import com.areyana.charger.view.ChargerApp
import com.areyana.design_system.ChargerTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val darkTheme = isSystemInDarkTheme()

            ChargerTheme {
                ChargerTheme(
                    darkTheme = darkTheme
                ) {
                    val backgroundColor = MaterialTheme.colorScheme.background
                    val systemUiController = rememberSystemUiController()
                    DisposableEffect(systemUiController) {
                        systemUiController.systemBarsDarkContentEnabled = !darkTheme
                        systemUiController.setSystemBarsColor(backgroundColor, darkIcons = !darkTheme)
                        onDispose {}
                    }
                    ChargerApp()
                }
            }
        }
    }
}