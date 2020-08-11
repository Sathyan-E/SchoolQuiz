package com.example.schoolquiz.room

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Result::class), version = 1)
abstract class AppDatabase :RoomDatabase() {
    abstract fun  resultDao(): ResultDao
}