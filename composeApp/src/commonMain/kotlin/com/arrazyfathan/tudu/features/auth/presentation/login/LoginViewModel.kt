package com.arrazyfathan.tudu.features.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.domain.utils.onError
import com.arrazyfathan.tudu.core.domain.utils.onSuccess
import com.arrazyfathan.tudu.core.presentation.toErrorMessage
import com.arrazyfathan.tudu.features.auth.domain.usecase.LoginUseCase
import com.arrazyfathan.tudu.features.auth.domain.usecase.ValidatePasswordUseCase
import com.arrazyfathan.tudu.features.auth.domain.usecase.ValidateUsernameUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val validateUsernameUseCase = ValidateUsernameUseCase()
    private val validatePasswordUseCase = ValidatePasswordUseCase()

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnLogin -> {
                if (validateUsername() && validatePassword()) {
                    login()
                }
            }

            is LoginAction.OnPasswordChange -> {
                _state.update { it.copy(password = action.password) }
                validatePassword()
            }

            is LoginAction.OnUsernameChange -> {
                _state.update { it.copy(username = action.username) }
                validateUsername()
            }
        }
    }

    fun dismissToast() {
        _state.update { it.copy(showToast = false) }
    }

    fun showToast() {
        _state.update { it.copy(showToast = true) }
    }

    private fun validateUsername(): Boolean {
        val emailResult = validateUsernameUseCase.execute(_state.value.username)
        _state.update { it.copy(usernameError = emailResult.errorMessage) }
        return emailResult.successful
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validatePasswordUseCase.execute(_state.value.password)
        _state.update { it.copy(passwordError = passwordResult.errorMessage) }
        return passwordResult.successful
    }

    private fun login() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            loginUseCase(
                LoginUseCase.Request(
                    username = _state.value.username,
                    password = _state.value.password,
                ),
            ).onSuccess {
                eventChannel.send(LoginEvent.LoginSuccess)
            }.onError { error ->
                val message = error.toErrorMessage()
                _state.update {
                    it.copy(
                        errorMessage = message,
                        isLoading = false,
                    )
                }
                eventChannel.send(LoginEvent.Error(message))
            }
        }
    }
}
