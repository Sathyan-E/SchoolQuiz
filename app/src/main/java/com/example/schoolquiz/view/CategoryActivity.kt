package com.example.schoolquiz.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.schoolquiz.R
import com.example.schoolquiz.adapter.CategoryAdapter
import com.example.schoolquiz.viewModel.CategoryActivityViewModel
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private lateinit var viewModel:CategoryActivityViewModel
    private lateinit var categoryAdapter:CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        viewModel=ViewModelProvider(this).get(CategoryActivityViewModel::class.java)

        check_button.setOnClickListener {
            viewModel.changeProgressState()
            viewModel.getCategoryList()
        }

        viewModel.showProgress.observe(this, Observer {
            if (it){
                category_progressbar.visibility=VISIBLE
            }else{
                category_progressbar.visibility=GONE
            }
        })
        viewModel.categoryList.observe(this, Observer {
          categoryAdapter.setCategoryList(it)
        })
        categoryAdapter= CategoryAdapter(this)
        category_recyclerview.adapter=categoryAdapter

    }
}