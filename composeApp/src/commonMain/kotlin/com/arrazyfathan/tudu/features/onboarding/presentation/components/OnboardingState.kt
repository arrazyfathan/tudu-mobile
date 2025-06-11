package com.arrazyfathan.tudu.features.onboarding.presentation.components

data class OnboardingState(
    val isChecking: Boolean = false,
    val isAuthenticated: Boolean = false,
    val isFirstTime: Boolean = true,
)