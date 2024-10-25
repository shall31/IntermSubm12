package com.dicoding.picodiploma.intermsubm1_2.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.intermsubm1_2.repository.AuthRepository
import com.dicoding.picodiploma.intermsubm1_2.response.DetailResponse
import com.dicoding.picodiploma.intermsubm1_2.response.Story
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(val authRepository: AuthRepository): ViewModel() {

    val detail = MutableLiveData<Story>()


    fun getDetail(id: String){
        val client = authRepository.postDetail(id)
        client.enqueue(object : Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful){
                    detail.value = response.body()?.story
                }else{
                    Log.d("Failure", response.message().toString())
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
    }

}