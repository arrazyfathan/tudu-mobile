package com.arrazyfathan.tudu.features.category.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.app.navigation.Routes

fun NavController.navigateToCategory(builder: NavOptionsBuilder.() -> Unit) = navigate(Routes.CategoryGraph, builder)

fun NavGraphBuilder.categoryGraph(navController: NavHostController) {
    navigation<Routes.CategoryGraph>(startDestination = Routes.Category) {
        composable<Routes.Category> {}
    }
}
