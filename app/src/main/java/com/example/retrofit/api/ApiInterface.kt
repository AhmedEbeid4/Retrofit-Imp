package com.example.retrofit.api

import com.example.retrofit.model.Post
import com.example.retrofit.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("posts")
    fun getPosts():Call<List<Post>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId:Int):Call<List<Post>>

    @GET("users")
    fun getUser(@Query("id") id :Int):Call<List<User>>
}