package com.example.schoolquiz.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.schoolquiz.R
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.layout_parameter_activity.*

class QuizParametersActivity : AppCompatActivity() {
    private var difficulty:String="easy"
    private var amount:String="10"
    private var type:String="multiple"
    private lateinit var categoryId:String
    private lateinit var categoryName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_parameter_activity)

        categoryId=intent.getStringExtra("category_id").toString()
        categoryName=intent.getStringExtra("category_name").toString()


        selected_category.text=categoryName

        start_quiz_button.setOnClickListener{
            if (student_name.text.toString() == ""){
                student_name.error="Enter your name"

            }
            else{
                val intent=Intent(this,QuizActivity::class.java)
                val name=student_name.text.toString()
                intent.putExtra("student_name",name)
                intent.putExtra("category_name",categoryName)
                intent.putExtra("category_id",categoryId)
                intent.putExtra("amount",amount)
                intent.putExtra("type",type)
                intent.putExtra("difficulty",difficulty)
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

    fun questionNumberSelection(view:View){
        if (view is RadioButton){
            val checked=view.isChecked

            when(view.id){
                R.id.num_10_rd_button ->
                    if (checked){
                        amount="10"
                    }
                R.id.num_15_rd_button ->
                    if (checked){
                        amount="15"
                    }
                R.id.num_20_rd_button ->
                    if (checked){
                        amount="20"
                    }
                R.id.num_25_rd_button ->
                    if (checked){
                        amount="25"
                    }
                R.id.num_30_rd_button ->
                    if (checked){
                        amount="30"
                    }
                R.id.num_40_rd_button ->
                    if (checked){
                        amount="40"
                    }
                R.id.num_50_rd_button ->
                    if (checked){
                        amount="50"
                    }
            }
        }
    }
    fun typeSelection(view:View){
        if (view is RadioButton){
            val checked=view.isChecked
            when(view.id){
                R.id.choice_radio_button ->
                    if (checked){
                        type="multiple"
                    }
                R.id.true_or_false_radio_button ->
                    if (checked){
                        type="boolean"
                    }

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
            Toast.makeText(this,"Signing out", Toast.LENGTH_SHORT).show()
        }

        return true
    }



}