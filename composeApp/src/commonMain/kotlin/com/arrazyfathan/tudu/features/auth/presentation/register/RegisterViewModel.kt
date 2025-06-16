package com.arrazyfathan.tudu.features.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.domain.utils.onError
import com.arrazyfathan.tudu.core.domain.utils.onSuccess
import com.arrazyfathan.tudu.features.auth.domain.usecase.RegisterUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnNameChange -> {
                _state.update { it.copy(name = action.name) }
            }

            is RegisterAction.OnPasswordChange -> {
                _state.update { it.copy(password = action.password) }
            }

            is RegisterAction.OnEmailChange -> {
                _state.update { it.copy(email = action.email) }
            }

            is RegisterAction.OnPasswordConfirmationChange -> {
                _state.update { it.copy(passwordConfirmation = action.passwordConfirmation) }
            }

            is RegisterAction.OnUsernameChange -> {
                _state.update { it.copy(username = action.username) }
            }

            RegisterAction.OnRegister -> {
            }
        }
    }

    fun dismissToast() {
        _state.update { it.copy(showToast = false) }
    }

    fun showToast() {
        _state.update { it.copy(showToast = true) }
    }

    fun register() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            registerUseCase(
                RegisterUseCase.Request(
                    username = state.value.username,
                    password = state.value.password,
                    email = state.value.email,
                    name = state.value.name,
                ),
            ).onSuccess {
                _state.update { it.copy(isLoading = false) }
                eventChannel.send(RegisterEvent.RegisterSuccess)
            }.onError {
                _state.update {
                    it.copy(
                        errorMessage = it.errorMessage,
                        isLoading = false,
                    )
                }
            }
        }
    }
}
