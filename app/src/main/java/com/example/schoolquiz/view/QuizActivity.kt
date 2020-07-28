package com.example.schoolquiz.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.schoolquiz.R
import com.example.schoolquiz.viewModel.QuizActivityViewModel
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private lateinit var quizviewModel:QuizActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        quizviewModel=ViewModelProvider(this).get(QuizActivityViewModel::class.java)
        val categoryId=intent.getStringExtra("category_id")
        val amount=intent.getStringExtra("amount")
        val type= intent.getStringExtra("type")
        val difficulty=intent.getStringExtra("difficulty")

        quizviewModel.getQuiz(categoryId!!,amount!!,difficulty!!,type!!)


        val name=intent.getStringExtra("category_id") +"Questions: "+"Question type: "+
                intent.getStringExtra("type")+"Difficulty: "+intent.getStringExtra("difficulty")
        mytext.text=name
    }
}