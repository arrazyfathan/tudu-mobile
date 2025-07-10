package com.arrazyfathan.tudu.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.arrazyfathan.tudu.app.AppViewModel
import com.arrazyfathan.tudu.core.data.networking.HttpClientFactory
import com.arrazyfathan.tudu.core.database.DatabaseFactory
import com.arrazyfathan.tudu.core.database.TuduDatabase
import com.arrazyfathan.tudu.core.preferences.PreferencesManager
import com.arrazyfathan.tudu.core.preferences.PreferencesManagerImpl
import com.arrazyfathan.tudu.core.preferences.SessionStorage
import com.arrazyfathan.tudu.core.preferences.SessionStorageImpl
import com.arrazyfathan.tudu.features.auth.data.repository.AuthenticationRepositoryImpl
import com.arrazyfathan.tudu.features.auth.domain.repository.AuthenticationRepository
import com.arrazyfathan.tudu.features.auth.domain.usecase.LoginUseCase
import com.arrazyfathan.tudu.features.auth.domain.usecase.RegisterUseCase
import com.arrazyfathan.tudu.features.auth.presentation.login.LoginViewModel
import com.arrazyfathan.tudu.features.auth.presentation.register.RegisterViewModel
import com.arrazyfathan.tudu.features.home.domain.usecase.LogoutUseCase
import com.arrazyfathan.tudu.features.home.presentation.homepage.HomePageViewModel
import com.arrazyfathan.tudu.features.onboarding.presentation.OnboardingViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module
expect val preferencesModule: Module

val dispatcherModule =
    module {
        single<CoroutineDispatcher>(qualifier = named("io")) { Dispatchers.IO }
    }

val sharedModule =
    module {
        single {
            HttpClientFactory(get()).build(get())
        }

        single<PreferencesManager> {
            PreferencesManagerImpl(get(), get(named("io")))
        }

        single<SessionStorage> {
            SessionStorageImpl(get(), get(named("io")))
        }

        single {
            get<DatabaseFactory>()
                .create()
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }

        single { get<TuduDatabase>().tuduDao }

        viewModelOf(::AppViewModel)
        viewModelOf(::OnboardingViewModel)

        singleOf(::AuthenticationRepositoryImpl).bind<AuthenticationRepository>()

        viewModelOf(::LoginViewModel)
        factoryOf(::LoginUseCase)

        viewModelOf(::RegisterViewModel)
        factoryOf(::RegisterUseCase)

        viewModelOf(::HomePageViewModel)
        factoryOf(::LogoutUseCase)
    }
