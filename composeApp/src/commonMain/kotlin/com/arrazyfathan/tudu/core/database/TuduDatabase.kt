package com.arrazyfathan.tudu.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TuduEntity::class],
    version = 1,
)
@TypeConverters(
    StringListTypeConverter::class,
)
@ConstructedBy(TuduDatabaseConstructor::class)
abstract class TuduDatabase : RoomDatabase() {
    abstract val tuduDao: TuduDao

    companion object {
        const val DB_NAME = "tudu.db"
    }
}
