package com.example.schoolquiz.network

import com.example.schoolquiz.network.model.Category
import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL="https://opentdb.com/api_category.php"
interface NetworkCalls {
    @GET
    fun getCategoryList():Call<List<Category>>
}