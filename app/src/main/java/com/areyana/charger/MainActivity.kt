package com.areyana.charger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.areyana.charger.view.ChargerApp
import com.areyana.design_system.ChargerTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
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
                    ChargerApp(calculateWindowSizeClass(this))
                }
            }
        }
    }
}