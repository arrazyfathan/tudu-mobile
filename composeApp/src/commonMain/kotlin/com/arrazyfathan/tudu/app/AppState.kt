package com.arrazyfathan.tudu.app

data class AppState(
    val isChecking: Boolean = false,
    val isAuthenticated: Boolean = false,
    val isOnboarded: Boolean = true,
)
