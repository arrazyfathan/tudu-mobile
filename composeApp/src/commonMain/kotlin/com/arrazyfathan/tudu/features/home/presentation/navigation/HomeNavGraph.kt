package com.arrazyfathan.tudu.features.home.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.app.navigation.Routes

fun NavController.navigateToHome(builder: NavOptionsBuilder.() -> Unit) = navigate(Routes.HomeGraph, builder)

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<Routes.HomeGraph>(
        startDestination = Routes.Home,
    ) {
        composable<Routes.Home> {
            Column {
                Text("Home")
            }
        }
    }
}
