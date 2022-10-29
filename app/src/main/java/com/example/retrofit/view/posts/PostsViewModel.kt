package com.example.retrofit.view.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.api.ApiClient
import com.example.retrofit.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel : ViewModel() {
    private val _posts=MutableLiveData<List<Post>>()
    val posts:LiveData<List<Post>>
    get() = _posts
    init {
        _posts.value=ArrayList()
    }
    fun getPosts(){
        GlobalScope.launch (Dispatchers.IO){
            ApiClient.getInstance()?.getPosts()?.enqueue(object :Callback<List<Post>>{
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    _posts.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    println("Error : ${t.message}")
                }
            })
        }
    }
}