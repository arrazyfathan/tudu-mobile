package com.arrazyfathan.tudu.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<TuduDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir =
            when {
                os.contains("win") -> File(System.getenv("APPDATA"), "Tudu")
                os.contains("mac") -> File(userHome, "Library/Application Support/Tudu")
                else -> File(userHome, ".local/share/Tudu")
            }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, TuduDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}
