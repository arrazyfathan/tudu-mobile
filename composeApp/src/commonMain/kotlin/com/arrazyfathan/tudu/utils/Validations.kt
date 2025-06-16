package com.arrazyfathan.tudu.utils

fun isNumber(value: String): Boolean = value.isEmpty() || Regex("^\\d+\$").matches(value)

fun isEmailValid(email: String): Boolean = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$").matches(email)

fun isPasswordValid(password: String): Boolean = password.any { it.isDigit() } && password.any { it.isLetter() }
