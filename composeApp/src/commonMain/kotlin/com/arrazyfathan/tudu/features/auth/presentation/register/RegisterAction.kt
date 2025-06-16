package com.arrazyfathan.tudu.features.auth.presentation.register

sealed interface RegisterAction {
    data object OnRegister : RegisterAction

    data class OnUsernameChange(
        val username: String,
    ) : RegisterAction

    data class OnNameChange(
        val name: String,
    ) : RegisterAction

    data class OnEmailChange(
        val email: String,
    ) : RegisterAction

    data class OnPasswordChange(
        val password: String,
    ) : RegisterAction

    data class OnPasswordConfirmationChange(
        val passwordConfirmation: String,
    ) : RegisterAction
}
