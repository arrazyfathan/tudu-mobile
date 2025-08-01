package com.arrazyfathan.tudu.features.tag.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.app.navigation.Routes

fun NavController.navigateToTag(builder: NavOptionsBuilder.() -> Unit) = navigate(Routes.TagGraph, builder)

fun NavGraphBuilder.tagGraph(navController: NavHostController) {
    navigation<Routes.TagGraph>(startDestination = Routes.Tag) {
        composable<Routes.Tag> {}
    }
}
