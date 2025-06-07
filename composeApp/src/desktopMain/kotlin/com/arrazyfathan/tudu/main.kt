package com.arrazyfathan.tudu

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arrazyfathan.tudu.di.initKoin
import org.koin.core.context.startKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Tudu",
        ) {
            App()
        }
    }
}