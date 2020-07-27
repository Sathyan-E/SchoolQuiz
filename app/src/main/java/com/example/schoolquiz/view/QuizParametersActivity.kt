package com.example.schoolquiz.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.schoolquiz.R
import kotlinx.android.synthetic.main.activity_quiz_parameter.*

class QuizParametersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_parameter)

        start_quiz_button.setOnClickListener{
            if (student_name.text.toString() == ""){
                student_name.error="Enter your name"
            }
        }
    }
}