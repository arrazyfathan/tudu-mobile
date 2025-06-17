package com.arrazyfathan.tudu.features.auth.domain.usecase

import com.arrazyfathan.tudu.core.domain.model.ValidationResult
import com.arrazyfathan.tudu.core.domain.usecase.BaseUseCase
import com.arrazyfathan.tudu.core.presentation.UiText
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.error_validation_name_blank

class ValidateNameUseCase : BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResourceId(Res.string.error_validation_name_blank),
            )
        }

        return ValidationResult(
            successful = true,
        )
    }
}
