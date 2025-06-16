package com.arrazyfathan.tudu.core.domain.usecase

import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.Result

interface UseCase<in P, R> {
    suspend operator fun invoke(request: P): Result<R?, DataError>
}
