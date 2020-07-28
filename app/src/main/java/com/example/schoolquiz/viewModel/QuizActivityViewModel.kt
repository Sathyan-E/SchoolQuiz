package com.example.schoolquiz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.schoolquiz.network.model.QuestionDetail
import com.example.schoolquiz.repository.QuizActivityRepository

class QuizActivityViewModel(application:Application):AndroidViewModel(application) {
    private val repository=QuizActivityRepository(application)
    val questionList:LiveData<List<QuestionDetail>>

    init {
        this.questionList=repository.questionList
    }

    fun  getQuiz(id:String,amount:String,difficulty:String,type:String){
        repository.getQuiz(id,amount, difficulty, type)
    }

}