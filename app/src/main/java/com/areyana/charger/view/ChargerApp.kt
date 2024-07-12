package com.areyana.charger.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.areyana.charger.navigation.ChargerNavHost
import com.areyana.design_system.component.ChargerBackground

@OptIn(
    ExperimentalComposeUiApi::class
)
@Composable
fun ChargerApp(
    nav: ChargerNavigation = rememberChargerNavigation()
) {
    ChargerBackground {
        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal + WindowInsetsSides.Vertical
                        )
                    )
            ) {
                ChargerNavHost(
                    navController = nav.navController,
                    modifier = Modifier
                        .consumeWindowInsets(padding)
                )
            }
        }
    }

}
