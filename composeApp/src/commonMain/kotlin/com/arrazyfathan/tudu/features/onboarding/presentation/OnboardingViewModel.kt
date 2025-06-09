package com.arrazyfathan.tudu.features.onboarding.presentation

import androidx.lifecycle.ViewModel
import com.arrazyfathan.tudu.core.preferences.AuthPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel(
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()

    fun onAction(action: OnboardingAction) {
    }

}