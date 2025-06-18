package com.arrazyfathan.tudu.features.home.presentation.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.domain.utils.onError
import com.arrazyfathan.tudu.core.domain.utils.onSuccess
import com.arrazyfathan.tudu.core.preferences.SessionStorage
import com.arrazyfathan.tudu.features.home.domain.usecase.LogoutUseCase
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val sessionStorage: SessionStorage,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val authInfo = sessionStorage.getAuthInfo()
            logoutUseCase(
                LogoutUseCase.Request(
                    refreshToken = authInfo?.refreshToken.orEmpty(),
                ),
            ).onSuccess {
                sessionStorage.clear()
                onSuccess()
            }.onError {
            }
        }
    }
}
