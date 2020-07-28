package com.example.schoolquiz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolquiz.R
import com.example.schoolquiz.network.model.Category
import com.example.schoolquiz.view.QuizParametersActivity
import kotlinx.android.synthetic.main.category_item_layout.view.*

class CategoryAdapter(private val context: Context):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var list:List<Category> = ArrayList()
    fun setCategoryList(list:List<Category>){
        this.list=list
        notifyDataSetChanged()
    }
    class CategoryViewHolder(v:View):RecyclerView.ViewHolder(v){
        val name: TextView =v.category_name
        val rootView: View =v.rootView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.category_item_layout,parent,false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.name.text=list[position].name
        holder.rootView.setOnClickListener{
            val intent=Intent(context,QuizParametersActivity::class.java)
            intent.putExtra("category_id",list[position].id.toString())
            intent.putExtra("category_name",list[position].name.toString())
            context.startActivity(intent)
        }



    }
}