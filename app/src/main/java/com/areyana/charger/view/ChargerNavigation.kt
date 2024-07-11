package com.areyana.charger.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberChargerNavigation(
    navController: NavHostController = rememberNavController()
): ChargerNavigation {
    return remember(navController) {
        ChargerNavigation(navController)
    }
}

@Stable
class ChargerNavigation(
    val navController: NavHostController
)