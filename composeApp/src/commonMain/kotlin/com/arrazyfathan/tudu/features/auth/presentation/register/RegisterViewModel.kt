package com.arrazyfathan.tudu.features.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.domain.utils.onError
import com.arrazyfathan.tudu.core.domain.utils.onSuccess
import com.arrazyfathan.tudu.core.presentation.toErrorMessage
import com.arrazyfathan.tudu.features.auth.domain.usecase.RegisterUseCase
import com.arrazyfathan.tudu.features.auth.domain.usecase.ValidateEmailUseCase
import com.arrazyfathan.tudu.features.auth.domain.usecase.ValidateNameUseCase
import com.arrazyfathan.tudu.features.auth.domain.usecase.ValidatePasswordConfirmationUseCase
import com.arrazyfathan.tudu.features.auth.domain.usecase.ValidatePasswordUseCase
import com.arrazyfathan.tudu.features.auth.domain.usecase.ValidateUsernameUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    private val validateUsername = ValidateUsernameUseCase()
    private val validateEmail = ValidateEmailUseCase()
    private val validatePassword = ValidatePasswordUseCase()
    private val validatePasswordConfirmation = ValidatePasswordConfirmationUseCase()
    private val validateName = ValidateNameUseCase()

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnNameChange -> {
                _state.update { it.copy(name = action.name) }
                validateName()
            }

            is RegisterAction.OnPasswordChange -> {
                _state.update { it.copy(password = action.password) }
                validatePassword()
                validatePasswordConfirmation()
            }

            is RegisterAction.OnEmailChange -> {
                _state.update { it.copy(email = action.email) }
                validateEmail()
            }

            is RegisterAction.OnPasswordConfirmationChange -> {
                _state.update { it.copy(passwordConfirmation = action.passwordConfirmation) }
                validatePasswordConfirmation()
            }

            is RegisterAction.OnUsernameChange -> {
                _state.update { it.copy(username = action.username) }
                validateUsername()
            }

            RegisterAction.OnRegister -> {
                if (validateEmail() && validateName() && validatePassword() && validatePasswordConfirmation()) {
                    register()
                }
            }
        }
    }

    fun dismissToast() {
        _state.update { it.copy(showToast = false) }
    }

    fun showToast() {
        _state.update { it.copy(showToast = true) }
    }

    private fun validateName(): Boolean {
        val nameResult = validateName.execute(_state.value.name)
        _state.update { it.copy(nameError = nameResult.errorMessage) }
        return nameResult.successful
    }

    private fun validateEmail(): Boolean {
        val emailResult = validateEmail.execute(_state.value.email)
        _state.update { it.copy(emailError = emailResult.errorMessage) }
        return emailResult.successful
    }

    private fun validateUsername(): Boolean {
        val emailResult = validateUsername.execute(_state.value.username)
        _state.update { it.copy(usernameError = emailResult.errorMessage) }
        return emailResult.successful
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validatePassword.execute(_state.value.password)
        _state.update { it.copy(passwordError = passwordResult.errorMessage) }
        return passwordResult.successful
    }

    private fun validatePasswordConfirmation(): Boolean {
        val passwordConfirmationResult =
            validatePasswordConfirmation.execute(
                Pair(
                    _state.value.password,
                    _state.value.passwordConfirmation,
                ),
            )
        _state.update { it.copy(passwordConfirmationError = passwordConfirmationResult.errorMessage) }
        return passwordConfirmationResult.successful
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
            }.onError { error ->
                val message = error.toErrorMessage()
                _state.update {
                    it.copy(
                        errorMessage = message,
                        isLoading = false,
                    )
                }
                eventChannel.send(RegisterEvent.Error(message))
            }
        }
    }
}
