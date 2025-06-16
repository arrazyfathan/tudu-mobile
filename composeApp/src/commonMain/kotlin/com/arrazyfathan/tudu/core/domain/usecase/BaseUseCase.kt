package com.arrazyfathan.tudu.core.domain.usecase

interface BaseUseCase<In, Out> {
    fun execute(input: In): Out
}
