package com.example.retrofit.api

import com.example.retrofit.model.Post
import com.example.retrofit.model.User
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiClient {
    private val BASE_URL="https://jsonplaceholder.typicode.com/"
    private lateinit var apiInterface:ApiInterface
    init {
        val retrofit:Retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface=retrofit.create(ApiInterface::class.java)
    }
    companion object{
        private  var INSTANCE :ApiClient?=null
        fun getInstance():ApiClient?{
            if(null == INSTANCE ){
                INSTANCE= ApiClient()
                return INSTANCE
            }
            return INSTANCE
        }
    }
    fun getPosts(): Call<List<Post>> {
        return apiInterface.getPosts()
    }
    fun getUserById(id:Int):Call<List<User>>{
        return apiInterface.getUser(id)
    }

}