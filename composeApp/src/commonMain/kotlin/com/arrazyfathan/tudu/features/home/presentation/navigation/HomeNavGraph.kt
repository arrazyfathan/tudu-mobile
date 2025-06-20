package com.arrazyfathan.tudu.features.home.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
    navigation<Routes.HomeGraph>(startDestination = Routes.Home, enterTransition = {
        slideInVertically(initialOffsetY = { it }, animationSpec = tween(700))
    }, exitTransition = {
        slideOutVertically(targetOffsetY = { it }, animationSpec = tween(700))
    }, popEnterTransition = {
        slideInVertically(initialOffsetY = { it }, animationSpec = tween(700))
    }, popExitTransition = {
        slideOutVertically(targetOffsetY = { it }, animationSpec = tween(700))
    }) {
        composable<Routes.Home> {
            HomePageScreen()
        }
    }
}
