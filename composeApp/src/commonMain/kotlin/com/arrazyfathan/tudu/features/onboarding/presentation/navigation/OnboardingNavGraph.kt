package com.arrazyfathan.tudu.features.onboarding.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.app.navigation.Routes
import com.arrazyfathan.tudu.features.auth.presentation.navigation.navigateToAuth
import com.arrazyfathan.tudu.features.onboarding.presentation.OnboardingScreen

fun NavGraphBuilder.onboardingGraph(navController: NavHostController) {
    navigation<Routes.OnboardingGraph>(
        startDestination = Routes.Onboarding,
    ) {
        composable<Routes.Onboarding> {
            OnboardingScreen(onNext = {
                navController.navigateToAuth {
                    popUpTo(Routes.OnboardingGraph) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            })
        }
    }
}
