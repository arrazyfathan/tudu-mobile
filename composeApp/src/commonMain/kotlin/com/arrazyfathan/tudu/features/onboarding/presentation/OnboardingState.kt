package com.arrazyfathan.tudu.features.onboarding.presentation

data class OnboardingState(
    val isChecking: Boolean = false,
    val isAuthenticated: Boolean = false,
    val isOnboarded: Boolean = true,
)
