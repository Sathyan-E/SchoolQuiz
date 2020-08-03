package com.example.schoolquiz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.schoolquiz.repository.HistoryActivityRepository

class HistoryActivityViewModel(application: Application): AndroidViewModel(application) {
    val historyActivityRepository=HistoryActivityRepository(application)

}