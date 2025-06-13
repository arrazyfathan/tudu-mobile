package com.arrazyfathan.tudu.features.auth.presentation.login

import com.arrazyfathan.tudu.core.presentation.UiText

sealed interface LoginEvent {
    data class Error(
        val error: UiText,
    ) : LoginEvent

    data object LoginSuccess : LoginEvent
}
