package com.example.retrofit.model

data class Post (val userId:Int,val title:String,val body:String){

    var id:Int=0

    constructor(id: Int,userId:Int,title: String,body: String):this(userId,title,body){
        this.id=id
    }
}