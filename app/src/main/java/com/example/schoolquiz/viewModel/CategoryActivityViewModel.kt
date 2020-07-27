package com.example.schoolquiz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.schoolquiz.repository.CategoryActivityRepository

class CategoryActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository=CategoryActivityRepository(application)
    val showProgress:LiveData<Boolean>
    init {
        this.showProgress=repository.showProgress
    }

    fun changeProgressState(){
        repository.changePrgressState()
    }
    fun getCategoryList(){
        repository.getCategoryList()
    }
}