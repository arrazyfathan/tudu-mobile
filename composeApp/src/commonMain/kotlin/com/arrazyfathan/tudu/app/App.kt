package com.arrazyfathan.tudu.app

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.arrazyfathan.tudu.app.navigation.NavigationRoot
import com.arrazyfathan.tudu.app.navigation.Routes
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.TuduTheme
import com.arrazyfathan.tudu.features.onboarding.presentation.OnboardingViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    TuduTheme {
        val viewModel = koinViewModel<OnboardingViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        val destination =
            if (state.isFirstTime) {
                Routes.OnboardingGraph
            } else if (!state.isFirstTime && !state.isAuthenticated) {
                Routes.AuthGraph
            } else {
                Routes.HomeGraph
            }

        if (!state.isChecking) {
            val navController = rememberNavController()
            Surface(
                modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing),
                color = AppColors.White,
                contentColor = Color.Black,
            ) {
                NavigationRoot(navController, destination)
            }
        }
    }
}
