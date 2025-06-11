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
    data object Auth : Routes
}