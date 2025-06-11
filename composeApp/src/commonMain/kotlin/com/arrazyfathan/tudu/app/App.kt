package com.arrazyfathan.tudu.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.arrazyfathan.tudu.core.ui.TuduTheme
import com.arrazyfathan.tudu.features.onboarding.presentation.OnboardingViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    TuduTheme {

        val viewModel = koinViewModel<OnboardingViewModel>()
        val state by viewModel.state.collectAsState()

        val destination = if (state.isFirstTime) {
            Routes.OnboardingGraph
        } else if (!state.isFirstTime && !state.isAuthenticated) {
            Routes.AuthGraph
        } else {
            Routes.HomeGraph
        }

        if (!state.isChecking) {
            val navController = rememberNavController()
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                ,
            ) { innerPadding ->
                NavigationRoot(navController, destination, innerPadding)
            }
        }
    }
}