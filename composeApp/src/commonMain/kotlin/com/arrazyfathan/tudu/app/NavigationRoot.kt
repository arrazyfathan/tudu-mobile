package com.arrazyfathan.tudu.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.features.auth.presentation.login.LoginScreen
import com.arrazyfathan.tudu.features.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController, startDestination = Routes.OnboardingGraph
    ) {
        onboardingGraph(navController)
        authGraph(navController)
    }
}

private fun NavGraphBuilder.onboardingGraph(
    navController: NavHostController
) {
    navigation<Routes.OnboardingGraph>(
        startDestination = Routes.Onboarding
    ) {
        composable<Routes.Onboarding> {
            OnboardingScreen(
                onNext = {
                    navController.navigate(Routes.AuthGraph)
                })
        }

    }
}

private fun NavGraphBuilder.authGraph(
    navController: NavHostController
) {
    navigation<Routes.AuthGraph>(
        startDestination = Routes.Auth
    ) {
        composable<Routes.Auth> {
            LoginScreen({}, {})
        }
    }
}