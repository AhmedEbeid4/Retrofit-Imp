package com.example.retrofit.view.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.api.ApiClient
import com.example.retrofit.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel :ViewModel() {
    private val _user= MutableLiveData<User>()
    val user : LiveData<User>
    get ()=_user


    fun setTheUserById(id:Int){
        ApiClient.getInstance()?.getUserById(id)?.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                _user.value=response.body()?.get(0) as User
                println("username user frag ${_user.value!!.username}")
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
            }

        })
    }
}