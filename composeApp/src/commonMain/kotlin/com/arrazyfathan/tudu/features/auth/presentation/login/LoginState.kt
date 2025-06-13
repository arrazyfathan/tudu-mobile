package com.arrazyfathan.tudu.features.auth.presentation.login

import com.arrazyfathan.tudu.core.presentation.UiText
import com.arrazyfathan.tudu.features.auth.domain.model.User

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val isLoggedIn: Boolean = false,
    val user: User? = null,
)
