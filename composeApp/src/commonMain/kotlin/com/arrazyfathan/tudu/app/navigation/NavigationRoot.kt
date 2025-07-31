package com.arrazyfathan.tudu.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arrazyfathan.tudu.features.about.presentation.navigation.aboutGraph
import com.arrazyfathan.tudu.features.auth.presentation.navigation.authGraph
import com.arrazyfathan.tudu.features.category.presentation.navigation.categoryGraph
import com.arrazyfathan.tudu.features.home.presentation.navigation.homeGraph
import com.arrazyfathan.tudu.features.journal.presentation.navigation.journalGraph
import com.arrazyfathan.tudu.features.onboarding.presentation.navigation.onboardingGraph
import com.arrazyfathan.tudu.features.profile.presentation.navigation.profileGraph
import com.arrazyfathan.tudu.utils.Material3Transitions

@Composable
fun NavigationRoot(
    navController: NavHostController,
    startDestination: Routes,
) {
    var previousScreen by remember { mutableStateOf<Routes?>(null) }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: startDestination
    val density = LocalDensity.current

    NavHost(navController = navController, startDestination = startDestination, enterTransition = {
        Material3Transitions.SharedXAxisEnterTransition(density)
    }, popEnterTransition = {
        Material3Transitions.SharedXAxisPopEnterTransition(density)
    }, exitTransition = {
        Material3Transitions.SharedXAxisExitTransition(density)
    }, popExitTransition = {
        Material3Transitions.SharedXAxisPopExitTransition(density)
    }) {
        onboardingGraph(navController)
        authGraph(navController)
        homeGraph(navController)
        profileGraph(navController)
        journalGraph(navController)
        aboutGraph(navController)
        categoryGraph(navController)
    }
}
