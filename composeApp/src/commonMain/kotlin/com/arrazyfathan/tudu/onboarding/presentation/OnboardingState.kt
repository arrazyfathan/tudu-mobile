package com.arrazyfathan.tudu.onboarding.presentation

import com.arrazyfathan.tudu.core.presentation.UiText

data class OnboardingState(
    val isUserAuthenticate: Boolean = false,
    val errorMessage: UiText? = null
)