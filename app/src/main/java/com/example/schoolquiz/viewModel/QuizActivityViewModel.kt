package com.example.schoolquiz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.schoolquiz.repository.QuizActivityRepository

class QuizActivityViewModel(application: Application):AndroidViewModel(application) {
    private val quizRepsitory=QuizActivityRepository(application)
}