package com.arrazyfathan.tudu.app.navigation

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

    @Serializable
    data object ProfileGraph : Routes

    @Serializable
    data object Profile : Routes

    @Serializable
    data object JournalGraph : Routes

    @Serializable
    data object Journal : Routes
}
