package com.arrazyfathan.tudu.features.onboarding.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.app.navigation.Routes
import com.arrazyfathan.tudu.features.onboarding.presentation.OnboardingScreen
import com.arrazyfathan.tudu.features.onboarding.presentation.OnboardingViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.onboardingGraph(navController: NavHostController) {
    navigation<Routes.OnboardingGraph>(
        startDestination = Routes.Onboarding,
    ) {
        composable<Routes.Onboarding> {
            val viewModel = koinViewModel<OnboardingViewModel>()
            OnboardingScreen(viewModel)
        }
    }
}
