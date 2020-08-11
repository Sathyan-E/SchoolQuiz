package com.example.schoolquiz.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_results")
data class ResultEntity(

    @ColumnInfo(name="student_name")
    var studentName:String?,
    @ColumnInfo(name = "category")
    var categoryName:String?,
    @ColumnInfo(name = "duration")
    var timeTaken:String?,
    @ColumnInfo(name = "quiz_result")
    var result:String?,
    @ColumnInfo(name = "quiz_score")
    var score:String?

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id:Int?=null
}