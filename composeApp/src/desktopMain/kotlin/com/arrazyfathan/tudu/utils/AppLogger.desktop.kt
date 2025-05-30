package com.arrazyfathan.tudu.utils

actual object AppLogger {
    actual fun d(tag: String, message: String) {
        println("DEBUG: [$tag] $message")
    }

    actual fun e(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            println("ERROR: [$tag] $message. Throwable: $throwable CAUSE ${throwable.cause}")
        } else {
            println("ERROR: [$tag] $message")
        }
    }

    actual fun i(tag: String, message: String) {
        println("INFO: [$tag] $message")
    }

    actual fun v(tag: String, message: String) {
        println("VERBOSE: [$tag] $message")
    }

    actual fun w(tag: String, message: String) {
        println("WARN: [$tag] $message")
    }
}