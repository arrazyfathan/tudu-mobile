package com.arrazyfathan.tudu.utils

import java.util.logging.Level
import java.util.logging.Logger

actual object AppLogger {
    private val logger: Logger = Logger.getLogger(AppLogger::class.java.name)

    actual fun v(tag: String, message: String) {
        logger.info("VERBOSE: [$tag] $message")
    }

    actual fun w(tag: String, message: String) {
        logger.warning("WARNING: [$tag] $message")
    }

    actual fun e(
        tag: String,
        message: String,
        throwable: Throwable?
    ) {
        if (throwable != null) {
            logger.log(Level.SEVERE, "ERROR: [$tag] $message", throwable)
        } else {
            logger.severe("ERROR: [$tag] $message")
        }
    }

    actual fun d(tag: String, message: String) {
        logger.info("DEBUG: [$tag] $message")
    }

    actual fun i(tag: String, message: String) {
        logger.info("INFO: [$tag] $message")
    }
}