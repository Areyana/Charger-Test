package com.areyana.charger.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.areyana.charger.view.charges.ChargesArg
import com.areyana.charger.view.charges.ChargesRoute
import com.areyana.charger.view.cities.CitiesDestination
import com.areyana.charger.view.cities.CitiesRoute
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ChargerNavHost(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = CitiesDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable<ChargesArg> {
            val arg: ChargesArg = it.toRoute()
            ChargesRoute(windowSizeClass = windowSizeClass, onBack = {
                navController.popBackStack()
            }, mvi = koinViewModel {
                parametersOf(arg)
            })
        }
        composable(route = CitiesDestination.route) {
            CitiesRoute(windowSizeClass = windowSizeClass, onNavigateToCharges = {
                navController.navigate(ChargesArg(it.city))
            })
        }
    }
}