package com.areyana.charger.view.charges

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChargesRoute(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
    onBack: () -> Unit,
    mvi: ChargesProcessor = koinViewModel()
) {

}