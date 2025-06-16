package com.arrazyfathan.tudu.core.domain.model

import com.arrazyfathan.tudu.core.presentation.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null,
)
