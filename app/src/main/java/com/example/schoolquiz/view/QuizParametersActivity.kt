package com.example.schoolquiz.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.example.schoolquiz.R
import kotlinx.android.synthetic.main.activity_quiz_parameter.*

class QuizParametersActivity : AppCompatActivity() {
    private var difficulty:String="easy"
    private var amount:String="10"
    private var type:String="multiple"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_parameter)

        start_quiz_button.setOnClickListener{
            if (student_name.text.toString() == ""){
                student_name.error="Enter your name"
            }
            else{
                val intent=Intent(this,QuizActivity::class.java)
                startActivity(intent)
            }
        }

    }

    fun difficultySelection(view:View){
        if (view is RadioButton){
            val checked=view.isChecked

            when(view.id){
                R.id.easy_radio_button ->
                    if (checked){
                        difficulty="easy"
                    }
                R.id.medium_radio_button ->
                    if (checked){
                        difficulty="medium"
                    }
                R.id.hard_radio_button ->
                    if (checked){
                        difficulty="hard"
                    }



            }
        }
    }
}