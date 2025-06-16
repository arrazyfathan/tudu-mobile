package com.arrazyfathan.tudu.features.auth.presentation.register

import com.arrazyfathan.tudu.core.presentation.UiText

data class RegisterState(
    val username: String = "",
    val usernameError: UiText? = null,
    val name: String = "",
    val nameError: UiText? = null,
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val passwordConfirmation: String = "",
    val passwordConfirmationError: UiText? = null,
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val showToast: Boolean = false,
)
