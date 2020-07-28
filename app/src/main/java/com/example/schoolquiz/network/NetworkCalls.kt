package com.example.schoolquiz.network

import com.example.schoolquiz.network.model.Quiz
import com.example.schoolquiz.network.model.TriviaCategories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL="https://opentdb.com/"
interface NetworkCalls {
    @GET("api_category.php")
    fun getCategoryList():Call<TriviaCategories>
    @GET("api.php?")
    fun getQuiz(@Query("amount") qnAmount:String,@Query("category") categoryId:String,@Query("difficulty") quizDifficulty:String,@Query("type") qnType:String ):Call<Quiz>
}