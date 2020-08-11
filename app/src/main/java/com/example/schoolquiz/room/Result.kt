package com.example.schoolquiz.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_results")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val uid:Int,

    @ColumnInfo(name="student_name")
    val studentName:String?,
    @ColumnInfo(name = "duration")
    val timeTaken:String?,
    @ColumnInfo(name = "quiz_result")
    val result:String?,
    @ColumnInfo(name = "quiz_score")
    val score:String?
)