package com.arrazyfathan.tudu.features.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.app.navigation.Routes
import com.arrazyfathan.tudu.features.auth.presentation.login.LoginScreen
import com.arrazyfathan.tudu.features.auth.presentation.login.LoginViewModel
import com.arrazyfathan.tudu.features.auth.presentation.register.RegisterScreen
import com.arrazyfathan.tudu.features.auth.presentation.register.RegisterViewModel
import com.arrazyfathan.tudu.features.home.presentation.navigation.navigateToHome
import org.koin.compose.viewmodel.koinViewModel

fun NavController.navigateToAuth(builder: NavOptionsBuilder.() -> Unit) = navigate(Routes.AuthGraph, builder)

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Routes.AuthGraph>(
        startDestination = Routes.Login,
    ) {
        composable<Routes.Login> {
            val viewModel = koinViewModel<LoginViewModel>()
            LoginScreen(
                onLoginSuccess = {
                    navController.navigateToHome {
                        popUpTo(Routes.AuthGraph) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onSignUp = {
                    navController.navigate(Routes.Register) {
                        launchSingleTop = true
                    }
                },
                viewModel = viewModel,
            )
        }

        composable<Routes.Register> {
            val viewModel = koinViewModel<RegisterViewModel>()
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.AuthGraph) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onBack = {
                    navController.navigateUp()
                },
                viewModel = viewModel,
            )
        }
    }
}
