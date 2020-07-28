package com.example.schoolquiz.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.schoolquiz.R
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val name=intent.getStringExtra("category_id") +"Questions: "+intent.getStringExtra("amount")+"Question type: "+
                intent.getStringExtra("type")+"Difficulty: "+intent.getStringExtra("difficulty")
        mytext.text=name
    }
}