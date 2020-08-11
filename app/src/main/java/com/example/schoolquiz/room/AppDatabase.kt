package com.example.schoolquiz.room

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = arrayOf(ResultEntity::class), version = 1,exportSchema = false)
abstract class AppDatabase :RoomDatabase() {
    abstract fun  resultDao(): ResultDao
}