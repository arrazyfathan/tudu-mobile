package com.arrazyfathan.tudu.utils

expect object AppLogger {
    fun d(
        tag: String,
        message: String,
    )

    fun e(
        tag: String,
        message: String,
        throwable: Throwable?,
    )

    fun i(
        tag: String,
        message: String,
    )

    fun v(
        tag: String,
        message: String,
    )

    fun w(
        tag: String,
        message: String,
    )
}
