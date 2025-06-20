package com.arrazyfathan.tudu.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.arrazyfathan.tudu.app.navigation.NavigationRoot
import com.arrazyfathan.tudu.app.navigation.Routes
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.TuduTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    TuduTheme {
        val viewModel = koinViewModel<AppViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val navController = rememberNavController()

        val destination =
            remember(state) {
                when {
                    !state.isOnboarded -> Routes.OnboardingGraph
                    !state.isAuthenticated -> Routes.AuthGraph
                    else -> Routes.HomeGraph
                }
            }

        if (!state.isChecking) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = AppColors.White,
                contentColor = Color.Black,
            ) {
                NavigationRoot(navController, destination)
            }
        }
    }
}
