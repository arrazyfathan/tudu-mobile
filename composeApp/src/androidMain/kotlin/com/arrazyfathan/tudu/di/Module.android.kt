package com.arrazyfathan.tudu.di

import createDataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() =
        module {
            single<HttpClientEngine> { OkHttp.create() }
        }
actual val preferencesModule: Module
    get() =
        module {
            single { createDataStore(androidContext()) }
        }
