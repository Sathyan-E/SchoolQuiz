package com.example.schoolquiz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.schoolquiz.repository.QuizParameterActivityRepository

class QuizParameterActivityViewModel(application: Application):AndroidViewModel(application) {
    private val quizRepsitory=QuizParameterActivityRepository(application)
}