package com.arrazyfathan.tudu.features.home.presentation.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.domain.utils.onError
import com.arrazyfathan.tudu.core.domain.utils.onSuccess
import com.arrazyfathan.tudu.core.preferences.AuthPreferences
import com.arrazyfathan.tudu.features.home.domain.usecase.LogoutUseCase
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val authPreferences: AuthPreferences,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val authInfo = authPreferences.getAuthInfo()
            logoutUseCase(
                LogoutUseCase.Request(
                    refreshToken = authInfo?.refreshToken.orEmpty(),
                ),
            ).onSuccess {
                authPreferences.clear()
                onSuccess()
            }.onError {
            }
        }
    }
}
