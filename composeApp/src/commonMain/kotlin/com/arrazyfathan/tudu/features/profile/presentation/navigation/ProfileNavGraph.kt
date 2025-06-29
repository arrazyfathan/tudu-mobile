package com.arrazyfathan.tudu.features.profile.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.app.navigation.Routes
import com.arrazyfathan.tudu.features.profile.presentation.ProfileScreen

fun NavController.navigateToProfile(builder: NavOptionsBuilder.() -> Unit) = navigate(Routes.ProfileGraph, builder)

fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation<Routes.ProfileGraph>(startDestination = Routes.Profile) {
        composable<Routes.Profile> {
            ProfileScreen(
                onBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}
