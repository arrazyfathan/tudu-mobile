package com.arrazyfathan.tudu.features.auth.domain.usecase

import com.arrazyfathan.tudu.core.domain.model.ValidationResult
import com.arrazyfathan.tudu.core.domain.usecase.BaseUseCase
import com.arrazyfathan.tudu.core.presentation.UiText
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.error_validation_password_blank
import tudu.composeapp.generated.resources.error_validation_password_confirmation_not_match

class ValidatePasswordConfirmationUseCase : BaseUseCase<Pair<String, String>, ValidationResult> {
    override fun execute(input: Pair<String, String>): ValidationResult {
        val (password, confirmPassword) = input

        if (confirmPassword.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResourceId(Res.string.error_validation_password_blank),
            )
        }

        if (password != confirmPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResourceId(Res.string.error_validation_password_confirmation_not_match),
            )
        }

        return ValidationResult(successful = true)
    }
}
