package com.arrazyfathan.tudu.features.auth.domain.usecase

import com.arrazyfathan.tudu.core.domain.model.ValidationResult
import com.arrazyfathan.tudu.core.domain.usecase.BaseUseCase
import com.arrazyfathan.tudu.core.presentation.UiText
import com.arrazyfathan.tudu.utils.isEmailValid
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.error_validation_email_blank
import tudu.composeapp.generated.resources.error_validation_email_not_valid

class ValidateEmailUseCase : BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResourceId(Res.string.error_validation_email_blank),
            )
        }

        if (!isEmailValid(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResourceId(Res.string.error_validation_email_not_valid),
            )
        }

        return ValidationResult(
            successful = true,
        )
    }
}
