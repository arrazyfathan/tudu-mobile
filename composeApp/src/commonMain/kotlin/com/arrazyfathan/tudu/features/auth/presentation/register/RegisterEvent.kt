package com.arrazyfathan.tudu.features.auth.presentation.register

import com.arrazyfathan.tudu.core.presentation.UiText

sealed interface RegisterEvent {
    data class Error(
        val error: UiText,
    ) : RegisterEvent

    data object RegisterSuccess : RegisterEvent
}
