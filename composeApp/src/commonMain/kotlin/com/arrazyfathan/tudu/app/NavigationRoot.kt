package com.arrazyfathan.tudu.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
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
    startDestination: Routes,
    innerPadding: PaddingValues,
) {


    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        onboardingGraph(navController)
        authGraph(navController)
        homeGraph(navController)
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
                    navController.navigate(Routes.AuthGraph) {
                        popUpTo(Routes.OnboardingGraph) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

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

private fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {
    navigation<Routes.HomeGraph>(
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            Column {
                Text("Home")
            }
        }
    }
}