package com.arrazyfathan.tudu.app

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object OnboardingGraph : Routes

    @Serializable
    data object Onboarding : Routes

    @Serializable
    data object AuthGraph : Routes

    @Serializable
    data object Login : Routes

    @Serializable
    data object Register : Routes

    @Serializable
    data object HomeGraph : Routes

    @Serializable
    data object Home : Routes
}
