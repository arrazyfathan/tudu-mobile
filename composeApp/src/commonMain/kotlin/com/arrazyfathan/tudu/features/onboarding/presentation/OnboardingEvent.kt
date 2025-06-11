package com.arrazyfathan.tudu.features.onboarding.presentation

sealed interface OnboardingEvent {
    data object OnSkipOnboarding : OnboardingEvent
}