package com.example.schoolquiz.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.example.schoolquiz.network.BASE_URL
import com.example.schoolquiz.network.NetworkCalls
import com.example.schoolquiz.network.model.QuestionDetail
import com.example.schoolquiz.network.model.Quiz
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizActivityRepository(val application: Application) {
        val questionList:ArrayList<QuestionDetail> = ArrayList()
    fun getQuiz(id:String,amount:String,difficulty:String,type:String):ArrayList<QuestionDetail>{
        questionList.clear()
        val retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service=retrofit.create(NetworkCalls::class.java)
        service.getQuiz(amount,id,difficulty,type).enqueue(object: Callback<Quiz>{
            override fun onFailure(call: Call<Quiz>, t: Throwable) {
                Toast.makeText(application,"Error while getting category list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
                questionList.addAll(response.body()?.results!!)
                Log.d("Category List","Response : ${Gson().toJson(response.body())}")

            }

        })
        return questionList
    }
}