package com.arrazyfathan.tudu.features.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.tudu.core.domain.utils.Result
import com.arrazyfathan.tudu.features.auth.domain.usecase.LoginUseCase
import com.arrazyfathan.tudu.utils.AppLogger
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLogin -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            val result = loginUseCase(
                LoginUseCase.Request(
                    username = "razy",
                    password = "qiqiq"
                )
            )

            when (result) {
                is Result.Error -> {
                    AppLogger.d("ApiLogger", result.error.toString())
                }

                is Result.Success -> {
                }
            }
        }
    }

}