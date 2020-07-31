package com.example.schoolquiz.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.*
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.schoolquiz.R
import com.example.schoolquiz.network.model.QuestionDetail
import com.example.schoolquiz.viewModel.QuizActivityViewModel
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private lateinit var quizviewModel:QuizActivityViewModel
    private var questionNumber=0
    private  var totalQuestions:Int =0
    private var questionList:ArrayList<QuestionDetail> = ArrayList()
    private var score=0
    private lateinit var correctAnswer:String
    private lateinit var studentAnswer:String
    private var displayRightAnswerMessage="Congratulations!! Perfect Answer"
    private lateinit var displayWrongAnswerMessage:String
    //private var startTime:Long=0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizviewModel=ViewModelProvider(this).get(QuizActivityViewModel::class.java)
        //chronometer.format="H:MM:SS"

        quizviewModel.questionList.observe(this, Observer {
            progressbar_quiz.visibility= GONE
            next_button.visibility= VISIBLE

            if (checkPermission()){
                loadQuiz()
            }
            else{
                requestPermission()
            }
            if (it.size>0){
                questionList.clear()
                questionNumber=0
                score=0
                questionList.addAll(it)
                totalQuestions=questionList.size
                updateUi(questionNumber)

            }
            else{
                Toast.makeText(this,"This Quiz is currently not Available",Toast.LENGTH_SHORT).show()
                error_textview.text="This Quiz is currently not Available"
                error_textview.visibility= VISIBLE
            }

        })
    }
    fun loadQuiz(){
        val categoryId=intent.getStringExtra("category_id")
        val amount=intent.getStringExtra("amount")
        val type= intent.getStringExtra("type")
        val difficulty=intent.getStringExtra("difficulty")
        progressbar_quiz.visibility= VISIBLE

        if (checkInternet()){
            quizviewModel.getQuiz(categoryId!!,amount!!,difficulty!!,type!!)
            error_textview.visibility= INVISIBLE
            refresh_button.visibility= INVISIBLE
            progressbar_quiz.visibility= INVISIBLE
        }
        else{
            error_textview.text="This app requires Internet connnection!"
            error_textview.visibility= VISIBLE
            refresh_button.visibility= VISIBLE
            progressbar_quiz.visibility= INVISIBLE
        }


    }

    fun updateUi(position:Int){
        chronometer.start()
        val questionObject=questionList.get(position)

        question_textview.text =questionObject.question
        val optionList=ArrayList<String>()
        optionList.addAll(questionObject.incorrectAnswers!!)
        correctAnswer=questionObject.correctAnswer!!
        optionList.add(questionObject.correctAnswer!!)
        optionList.sort()
        // optionList.sortBy { it.label.toString() }
        if (questionObject.type=="boolean"){
            optionsForBoolean(optionList)
        }
        else {
            optionsForMultiple(optionList)
        }

    }
    fun optionsForBoolean(list:List<String>){
        option_A.text=list.get(0)
        option_B.text=list.get(1)
        option_C.visibility= GONE
        option_D.visibility= GONE
        qn_card_view.visibility= VISIBLE
    }
    fun optionsForMultiple(list:List<String>){
        option_C.visibility= VISIBLE
        option_D.visibility= VISIBLE
        option_A.text=list.get(0)
        option_B.text=list.get(1)
        option_C.text=list.get(2)
        option_D.text=list.get(3)

        qn_card_view.visibility= VISIBLE

    }

    fun submitAnswer(view:View){
        submit_button.isEnabled=false
        questionNumber++
        next_button.isEnabled=true
        val id= findViewById<RadioButton>(answer_radio_group.checkedRadioButtonId)
        studentAnswer=id.text.toString()

        if(studentAnswer==correctAnswer){
            Toast.makeText(this, "Correct Answer!",Toast.LENGTH_SHORT).show()
            score++
            result_textview.text=displayRightAnswerMessage
            result_textview.visibility=VISIBLE
            result_textview.setTextColor(resources.getColor(R.color.dark_green))
        }else{
            Toast.makeText(this, "Wrong Answer!$correctAnswer",Toast.LENGTH_SHORT).show()
            displayWrongAnswerMessage="Wrong Answer! The correct answer is $correctAnswer"
            result_textview.text=displayWrongAnswerMessage
            result_textview.visibility=VISIBLE
            result_textview.setTextColor(resources.getColor(R.color.wrongAnswer))
        }
        if (questionNumber>=totalQuestions){
            next_button.text="Finish"
        }


    }

    fun goNextQuestion(view:View){
        if (questionNumber<totalQuestions){
            updateUi(questionNumber)
            view.isEnabled=false
            answer_radio_group.clearCheck()
            result_textview.visibility=View.INVISIBLE
        }
        else{
            chronometer.stop()
            Toast.makeText(this,"Quiz Ended.Your  score is $score",Toast.LENGTH_SHORT).show()
            //result_textview.text="Quiz Ended.Your  score is $score"
            publishResult()
        }

    }

    fun buttonClicked(view: View){
        if (view is RadioButton){
            submit_button.isEnabled=true
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.sign_out){

            AuthUI.getInstance().signOut(this)
            Toast.makeText(this,"Sign out button clciked",Toast.LENGTH_SHORT).show()
        }

        return true
    }
    fun publishResult(){
        category_result.text=intent.getStringExtra("category_name")
        name_result.text=intent.getStringExtra("student_name")
        score_result.text=score.toString()
        time_taken.text=chronometer.text
        if (score>=5){
            final_result.text="PASS"
        }else{
            final_result.text="FAIL"
        }
        result_cardview.visibility= VISIBLE
    }

    private fun checkInternet():Boolean{
        val cm= applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork=cm.activeNetworkInfo
        val isConnected:Boolean=activeNetwork?.isConnectedOrConnecting==true
        return isConnected
    }

    fun refreshButtonClicked(view: View){
        loadQuiz()
    }

    override fun onPause() {
        super.onPause()
        chronometer.stop()
    }

    override fun onResume() {
        super.onResume()
        loadQuiz()
    }
    private fun checkPermission():Boolean{
        if (
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED)
        {
            return true
        }
        return false
    }
    //requesting permission for location
    private  fun requestPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET),1
        )
    }

}