package com.arrazyfathan.tudu.features.home.domain.repository

import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.Result
import com.arrazyfathan.tudu.features.home.domain.model.Journal

interface JournalRepository {
    fun getJournals(): Result<List<Journal>?, DataError>
}
