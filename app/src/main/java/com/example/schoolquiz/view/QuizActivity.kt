package com.example.schoolquiz.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.schoolquiz.R
import com.example.schoolquiz.network.model.QuestionDetail
import com.example.schoolquiz.viewModel.QuizActivityViewModel
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private lateinit var quizviewModel:QuizActivityViewModel
    private var questionNumber=0
    private  var totalQuestions:Int =0
    private var questionList:ArrayList<QuestionDetail> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizviewModel=ViewModelProvider(this).get(QuizActivityViewModel::class.java)
        val categoryId=intent.getStringExtra("category_id")
        val amount=intent.getStringExtra("amount")
        val type= intent.getStringExtra("type")
        val difficulty=intent.getStringExtra("difficulty")

        quizviewModel.getQuiz(categoryId!!,amount!!,difficulty!!,type!!)

        quizviewModel.questionList.observe(this, Observer {
            questionList.clear()
            questionNumber=0
            questionList.addAll(it)
            totalQuestions=questionList.size
            updateUi(questionNumber)
        })

    }

    fun updateUi(position:Int){

        question_textview.text =questionList.get(position).question
        var optionList=ArrayList<String>()
        optionList.addAll(questionList.get(position).incorrectAnswers!!)
        optionList.add(questionList.get(position).correctAnswer!!)
        // optionList.sortBy { it.label.toString() }
        option_A.text=optionList.get(0)
        option_B.text=optionList.get(1)
        option_C.text=optionList.get(2)
        option_D.text=optionList.get(3)

    }

    fun submitAnswer(view:View){
        questionNumber++
        next_button.isEnabled=true

    }

    fun goNextQuestion(view:View){
        if (questionNumber<totalQuestions){
            updateUi(questionNumber)
        }

    }
}