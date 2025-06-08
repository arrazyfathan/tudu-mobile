package com.arrazyfathan.tudu.di

import com.arrazyfathan.tudu.core.data.HttpClientFactory
import com.arrazyfathan.tudu.core.preferences.PreferencesManager
import com.arrazyfathan.tudu.core.preferences.PreferencesManagerImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

expect val platformModule: Module
expect val preferencesModule: Module

val dispatcherModule = module {
    single<CoroutineDispatcher>(qualifier = named("io")) { Dispatchers.IO }
}

val sharedModule = module {
    single {
        HttpClientFactory.create(get())
    }

    single<PreferencesManager> {
        PreferencesManagerImpl(get(), get(named("io")))
    }
}