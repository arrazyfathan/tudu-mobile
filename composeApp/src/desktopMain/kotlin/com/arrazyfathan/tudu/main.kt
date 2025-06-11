package com.arrazyfathan.tudu

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arrazyfathan.tudu.app.App
import com.arrazyfathan.tudu.di.initKoin

fun main() {
    initKoin()
    application {
        val state = rememberWindowState(
            size = DpSize(
                width = 411.dp,
                height = 823.dp
            )
        )
        Window(
            state = state,
            alwaysOnTop = true,
            onCloseRequest = ::exitApplication,
            title = "Tudu",
        ) {
            App()
        }
    }
}