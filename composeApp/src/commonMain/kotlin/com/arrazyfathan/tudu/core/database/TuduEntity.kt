package com.arrazyfathan.tudu.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TuduEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
)
