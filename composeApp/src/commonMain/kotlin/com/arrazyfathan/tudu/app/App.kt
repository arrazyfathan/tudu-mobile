package com.arrazyfathan.tudu.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.arrazyfathan.tudu.onboarding.presentation.OnboardingScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = Routes.OnboardingGraph
        ) {
            navigation<Routes.OnboardingGraph>(
                startDestination = Routes.Onboarding
            ) {
                composable<Routes.Onboarding> {
                    OnboardingScreen()
                }
            }
        }
    }
}