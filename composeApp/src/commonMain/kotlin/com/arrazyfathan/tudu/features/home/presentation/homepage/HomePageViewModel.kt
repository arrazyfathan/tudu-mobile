package com.arrazyfathan.tudu.features.home.presentation.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.preferences.AuthPreferences
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            authPreferences.clear()
            onSuccess()
        }
    }
}
