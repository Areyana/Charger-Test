package com.areyana.charger.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.areyana.charger.view.charges.ChargesDestination
import com.areyana.charger.view.charges.ChargesRoute
import com.areyana.charger.view.cities.CitiesDestination
import com.areyana.charger.view.cities.CitiesRoute

@Composable
fun ChargerNavHost(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ChargesDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = ChargesDestination.route) {
            ChargesRoute(windowSizeClass = windowSizeClass, onBack = {
                navController.popBackStack()
            })
        }
        composable(route = CitiesDestination.route) {
            CitiesRoute(windowSizeClass = windowSizeClass)
        }
    }
}