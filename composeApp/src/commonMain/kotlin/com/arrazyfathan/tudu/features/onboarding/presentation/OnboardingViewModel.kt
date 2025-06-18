package com.arrazyfathan.tudu.features.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.preferences.PreferencesManager
import com.arrazyfathan.tudu.core.preferences.SessionStorage
import com.arrazyfathan.tudu.features.onboarding.presentation.components.OnboardingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val preferencesManager: PreferencesManager,
    private val sessionStorage: SessionStorage,
) : ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { state -> state.copy(isChecking = true) }
            _state.update { state ->
                state.copy(
                    isAuthenticated = sessionStorage.isAuthenticated(),
                    isFirstTime = preferencesManager.isFirstTime.first(),
                )
            }
            _state.update { state -> state.copy(isChecking = false) }
        }
    }

    fun onEvent(action: OnboardingEvent) {
        when (action) {
            OnboardingEvent.OnSkipOnboarding -> {
                changeOnboardingStatus()
            }
        }
    }

    private fun changeOnboardingStatus() {
        viewModelScope.launch {
            preferencesManager.setIsFirstTime(false)
        }
    }
}
