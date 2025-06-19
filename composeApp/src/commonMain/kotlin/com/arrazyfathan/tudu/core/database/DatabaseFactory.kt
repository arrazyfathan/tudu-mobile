package com.arrazyfathan.tudu.core.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<TuduDatabase>
}
