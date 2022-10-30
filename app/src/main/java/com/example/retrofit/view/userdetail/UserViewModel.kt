package com.example.retrofit.view.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.api.ApiClient
import com.example.retrofit.model.Post
import com.example.retrofit.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel :ViewModel() {
    private val _user= MutableLiveData<User>()
    val user : LiveData<User>
    get ()=_user

    private val _posts= MutableLiveData<List<Post>>()
    val posts : LiveData<List<Post>>
        get ()=_posts

    fun setTheUserById(id:Int){
        GlobalScope.launch(Dispatchers.IO) {
            ApiClient.getInstance()?.getUserById(id)?.enqueue(object : Callback<List<User>>{
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    _user.postValue(response.body()?.get(0) as User)
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                }

            })
            getPostsOfTheUser(id)
        }
    }
    init {
        _posts.value = ArrayList()
    }
    private fun getPostsOfTheUser(id:Int){
            ApiClient.getInstance()?.getPostsByUserId(id)?.enqueue(object :Callback<List<Post>>{
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    val arr =response.body()!!
                    println("List :- $arr")
                    _user.value?.posts = arr
                    _posts.value = arr
                    println("list size = ${_posts.value?.size}")

                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {

                }

            })
        }

}