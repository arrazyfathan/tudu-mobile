package com.arrazyfathan.tudu.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.preferences.PreferencesManager
import com.arrazyfathan.tudu.core.preferences.SessionStorage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AppViewModel(
    preferencesManager: PreferencesManager,
    sessionStorage: SessionStorage,
) : ViewModel() {
    val state =
        combine(
            preferencesManager.isOnboarded,
            sessionStorage.isAuthenticated(),
        ) { isOnboarded, isAuthenticated ->
            AppState(
                isOnboarded = isOnboarded,
                isAuthenticated = isAuthenticated,
                isChecking = false,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(300),
            initialValue = AppState(isChecking = true),
        )
}
