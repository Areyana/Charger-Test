package com.areyana.charger.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.areyana.navigation.ChargerNavigationDestination

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
) {
    fun navigate(destination: ChargerNavigationDestination, route: String? = null) {
        navController.navigate(route ?: destination.route)
    }
}