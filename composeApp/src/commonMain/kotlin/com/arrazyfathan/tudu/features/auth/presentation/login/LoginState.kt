package com.arrazyfathan.tudu.features.auth.presentation.login

import com.arrazyfathan.tudu.core.presentation.UiText

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val showToast: Boolean = false,
)
