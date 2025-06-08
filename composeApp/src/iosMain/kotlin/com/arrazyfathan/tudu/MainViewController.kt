package com.arrazyfathan.tudu

import androidx.compose.ui.window.ComposeUIViewController
import com.arrazyfathan.tudu.app.App
import com.arrazyfathan.tudu.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }) { App() }