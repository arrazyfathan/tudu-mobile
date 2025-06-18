package com.arrazyfathan.tudu.features.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.preferences.PreferencesManager
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {
    fun onEvent(action: OnboardingEvent) {
        when (action) {
            OnboardingEvent.OnSkipOnboarding -> {
                changeOnboardingStatus()
            }
        }
    }

    private fun changeOnboardingStatus() {
        viewModelScope.launch {
            preferencesManager.setIsOnboarded(true)
        }
    }
}
