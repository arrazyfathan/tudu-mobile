package com.arrazyfathan.tudu.features.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.preferences.PreferencesManager
import com.arrazyfathan.tudu.core.preferences.SessionStorage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val preferencesManager: PreferencesManager,
    sessionStorage: SessionStorage,
) : ViewModel() {
    val state =
        combine(
            preferencesManager.isOnboarded,
            sessionStorage.isAuthenticated(),
        ) { isOnboarded, isAuthenticated ->
            OnboardingState(
                isOnboarded = isOnboarded,
                isAuthenticated = isAuthenticated,
                isChecking = false,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = OnboardingState(isChecking = true),
        )

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
