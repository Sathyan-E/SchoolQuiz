package com.example.schoolquiz.network.model

import com.google.gson.annotations.SerializedName

class QuestionDetail {
    @SerializedName("category")
    var category:String?=null
    @SerializedName("type")
    var type:String?=null
    @SerializedName("difficulty")
    var difficulty:String?=null
    @SerializedName("question")
    var question:String?=null
    @SerializedName("correct_answer")
    var  correctAnswer:String?=null
    @SerializedName("incorrect_answer")
    var incorrectAnswers:List<String>?=null
}