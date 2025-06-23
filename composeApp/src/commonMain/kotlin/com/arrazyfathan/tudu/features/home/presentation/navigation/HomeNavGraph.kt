package com.arrazyfathan.tudu.features.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.app.navigation.Routes
import com.arrazyfathan.tudu.features.home.presentation.homepage.HomePageScreen

fun NavController.navigateToHome(builder: NavOptionsBuilder.() -> Unit) = navigate(Routes.HomeGraph, builder)

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<Routes.HomeGraph>(startDestination = Routes.Home) {
        composable<Routes.Home> {
            HomePageScreen()
        }
    }
}
