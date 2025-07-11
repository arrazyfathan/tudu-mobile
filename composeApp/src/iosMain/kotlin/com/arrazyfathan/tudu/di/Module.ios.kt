package com.arrazyfathan.tudu.di

import com.arrazyfathan.tudu.core.database.DatabaseFactory
import createDataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() =
        module {
            single<HttpClientEngine> { Darwin.create() }
            single { DatabaseFactory() }
        }

actual val preferencesModule: Module
    get() =
        module {
            single { createDataStore() }
        }
