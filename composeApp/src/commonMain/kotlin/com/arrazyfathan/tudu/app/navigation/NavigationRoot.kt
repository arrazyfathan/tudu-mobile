package com.arrazyfathan.tudu.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arrazyfathan.tudu.features.auth.presentation.navigation.authGraph
import com.arrazyfathan.tudu.features.home.presentation.navigation.homeGraph
import com.arrazyfathan.tudu.features.onboarding.presentation.navigation.onboardingGraph

@Composable
fun NavigationRoot(
    navController: NavHostController,
    startDestination: Routes,
) {
    var previousScreen by remember { mutableStateOf<Routes?>(null) }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: startDestination

    NavHost(navController = navController, startDestination = startDestination, enterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(500),
        )
    }, exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(500),
        )
    }, popEnterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(500),
        )
    }, popExitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(500),
        )
    }) {
        onboardingGraph(navController)
        authGraph(navController)
        homeGraph(navController)
    }
}
