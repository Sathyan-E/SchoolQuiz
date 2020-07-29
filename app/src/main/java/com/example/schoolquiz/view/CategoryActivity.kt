package com.example.schoolquiz.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.schoolquiz.R
import com.example.schoolquiz.adapter.CategoryAdapter
import com.example.schoolquiz.viewModel.CategoryActivityViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private lateinit var viewModel:CategoryActivityViewModel
    private lateinit var categoryAdapter:CategoryAdapter
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var authStateListener:FirebaseAuth.AuthStateListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        firebaseAuth=FirebaseAuth.getInstance()

        viewModel=ViewModelProvider(this).get(CategoryActivityViewModel::class.java)
        viewModel.changeProgressState()
        viewModel.getCategoryList()

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

    override fun onResume() {
        super.onResume()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onPause() {
        super.onPause()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    override fun onStart() {
        super.onStart()
        authStateListener=FirebaseAuth.AuthStateListener {
                firebaseAuth ->
            val muser=firebaseAuth.currentUser
            if (muser!=null){
                Log.i("Main Activity","User is already signed in")
            }else{
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .build(),10
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==10){
            if (resultCode== Activity.RESULT_OK){
                Toast.makeText(this,"You're Signed In", Toast.LENGTH_SHORT).show()
            }
            else if (resultCode== Activity.RESULT_CANCELED){
                Toast.makeText(this,"Sign In Cancelled", Toast.LENGTH_SHORT).show()
                finish()
            }
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
}