package com.arrazyfathan.tudu.di

import DATA_STORE_FILE_NAME
import createDataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() =
        module {
            single<HttpClientEngine> { CIO.create() }
        }
actual val preferencesModule: Module
    get() =
        module {
            single { createDataStore { DATA_STORE_FILE_NAME } }
        }
