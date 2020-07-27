package com.example.schoolquiz.network.model

import com.google.gson.annotations.SerializedName

class TriviaCategories {
    @SerializedName("trivia_categories")
    var categoryList:List<Category>? = null
}