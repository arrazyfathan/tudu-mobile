package com.arrazyfathan.tudu.features.home.presentation.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.domain.utils.onError
import com.arrazyfathan.tudu.core.domain.utils.onSuccess
import com.arrazyfathan.tudu.core.preferences.SessionStorage
import com.arrazyfathan.tudu.features.home.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val sessionStorage: SessionStorage,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun logout() {
        viewModelScope.launch {
            val authInfo = sessionStorage.getAuthInfo()
            val refreshToken = authInfo.first()?.refreshToken.orEmpty()
            logoutUseCase(
                LogoutUseCase.Request(
                    refreshToken = refreshToken,
                ),
            ).onSuccess {
                sessionStorage.clear()
            }.onError {}
        }
    }

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.OnCreateNewJournal -> {
            }

            HomeAction.OnLogout -> logout()
            HomeAction.OnProfileClick -> {}
            HomeAction.OnSearch -> {}
        }
    }
}
