package com.example.schoolquiz.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData

class CategoryActivityRepository(application: Application) {
    val showProgress=MutableLiveData<Boolean>()

    fun changePrgressState(){
        showProgress.value = !(showProgress.value!=null && showProgress.value!!)
    }


}