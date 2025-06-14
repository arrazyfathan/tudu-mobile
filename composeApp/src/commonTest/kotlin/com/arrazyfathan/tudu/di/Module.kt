package com.arrazyfathan.tudu.di

import com.arrazyfathan.tudu.features.auth.data.FakeAuthRepository
import com.arrazyfathan.tudu.features.auth.domain.repository.AuthenticationRepository
import org.koin.dsl.module

val overrideModule = module {
    single<AuthenticationRepository> { FakeAuthRepository() }
}