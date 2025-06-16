package com.arrazyfathan.tudu.features.auth.domain.usecase

import com.arrazyfathan.tudu.core.domain.model.ValidationResult
import com.arrazyfathan.tudu.core.domain.usecase.BaseUseCase
import com.arrazyfathan.tudu.core.presentation.UiText
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.error_validation_password_blank
import tudu.composeapp.generated.resources.error_validation_password_too_short

class ValidatePasswordUseCase : BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResourceId(Res.string.error_validation_password_blank),
            )
        }
        if (input.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResourceId(Res.string.error_validation_password_too_short),
            )
        }

        return ValidationResult(
            successful = true,
        )
    }
}
