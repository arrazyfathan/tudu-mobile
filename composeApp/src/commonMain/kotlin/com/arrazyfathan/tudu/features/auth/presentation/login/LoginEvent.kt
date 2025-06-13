package com.arrazyfathan.tudu.features.auth.presentation.login

sealed interface LoginEvent {
    data object OnLogin : LoginEvent
}