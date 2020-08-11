package com.example.schoolquiz.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao {
    @Query("SELECT * FROM  history_results")
    fun getAll():List<ResultEntity>
    @Insert
    fun insertResult(vararg result: ResultEntity)
}