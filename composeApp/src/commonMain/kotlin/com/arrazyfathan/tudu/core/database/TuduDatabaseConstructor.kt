package com.arrazyfathan.tudu.core.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object TuduDatabaseConstructor : RoomDatabaseConstructor<TuduDatabase> {
    override fun initialize(): TuduDatabase
}
