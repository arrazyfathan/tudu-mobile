package com.arrazyfathan.tudu.utils

fun String?.toGenericError() = this ?: "Unknown Error"

fun String.removeAllSpaces() = this.replace(" ", "")
