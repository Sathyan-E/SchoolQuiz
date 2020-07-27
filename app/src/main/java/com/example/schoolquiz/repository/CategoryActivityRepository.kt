package com.example.schoolquiz.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.schoolquiz.network.BASE_URL
import com.example.schoolquiz.network.NetworkCalls
import com.example.schoolquiz.network.model.Category
import com.example.schoolquiz.network.model.TriviaCategories
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryActivityRepository(val application: Application) {
    val showProgress=MutableLiveData<Boolean>()

    fun changePrgressState(){
        showProgress.value = !(showProgress.value!=null && showProgress.value!!)
    }
    fun getCategoryList(){
        showProgress.value=true
        //network call
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        val service=retrofit.create(NetworkCalls::class.java)

        service.getCategoryList().enqueue(object :Callback<TriviaCategories>{
            override fun onFailure(call: Call<TriviaCategories>, t: Throwable) {
                showProgress.value=false
                t.message?.let { Log.e("Error", it) }
                Toast.makeText(application,"Error while getting category list",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<TriviaCategories>,
                response: Response<TriviaCategories>) {

                showProgress.value=false
                Log.d("Category List","Response : ${Gson().toJson(response.body())}")
            }

        })
    }


}