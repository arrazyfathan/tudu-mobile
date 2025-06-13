package com.arrazyfathan.tudu.features.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.domain.utils.onError
import com.arrazyfathan.tudu.core.domain.utils.onSuccess
import com.arrazyfathan.tudu.core.presentation.toErrorMessage
import com.arrazyfathan.tudu.features.auth.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLogin -> {
                login()
            }
        }
    }

    private fun login() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            loginUseCase(
                LoginUseCase.Request(
                    username = "",
                    password = "",
                ),
            ).onSuccess { user ->
                _state.update {
                    it.copy(
                        user = user,
                        isLoading = false,
                    )
                }
            }.onError { error ->
                val message = error.toErrorMessage()
                _state.update {
                    it.copy(
                        errorMessage = message,
                        isLoading = false,
                    )
                }
            }
        }
    }
}
