package com.arrazyfathan.tudu.features.auth.presentation.login

sealed interface LoginAction {
    data object OnLogin : LoginAction

    data class OnUsernameChange(
        val username: String,
    ) : LoginAction

    data class OnPasswordChange(
        val password: String,
    ) : LoginAction
}
