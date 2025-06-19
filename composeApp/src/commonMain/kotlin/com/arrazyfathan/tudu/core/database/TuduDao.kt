package com.arrazyfathan.tudu.core.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TuduDao {
    @Upsert
    suspend fun upsert(book: TuduEntity)

    @Query("SELECT * FROM TuduEntity")
    fun get(): Flow<List<TuduEntity>>

    @Query("DELETE FROM TuduEntity WHERE id = :id")
    suspend fun delete(id: String)
}
