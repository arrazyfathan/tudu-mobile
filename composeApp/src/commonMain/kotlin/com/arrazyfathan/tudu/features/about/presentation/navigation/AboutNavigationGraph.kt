package com.arrazyfathan.tudu.features.about.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.app.navigation.Routes

fun NavController.navigateToAbout(builder: NavOptionsBuilder.() -> Unit) = navigate(Routes.AboutGraph, builder)

fun NavGraphBuilder.aboutGraph(navController: NavHostController) {
    navigation<Routes.AboutGraph>(startDestination = Routes.About) {
        composable<Routes.About> {
        }
    }
}
