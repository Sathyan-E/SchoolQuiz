package com.example.schoolquiz.network.model

import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("id")
    var id:Int=0
    @SerializedName("name")
    var name:String?=null
}