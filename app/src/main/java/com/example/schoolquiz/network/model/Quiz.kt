package com.example.schoolquiz.network.model

import com.google.gson.annotations.SerializedName

class Quiz {
    @SerializedName("response_code")
    var responseCode:Int?=0
    @SerializedName("results")
    var results:List<QuestionDetail>?=null
}