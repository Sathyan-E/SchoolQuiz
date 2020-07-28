package com.example.schoolquiz.network

import com.example.schoolquiz.network.model.Quiz
import com.example.schoolquiz.network.model.TriviaCategories
import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL="https://opentdb.com/"
interface NetworkCalls {
    @GET("api_category.php")
    fun getCategoryList():Call<TriviaCategories>
    fun getQuiz():Call<Quiz>
}