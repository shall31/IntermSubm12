package com.dicoding.picodiploma.intermsubm1_2.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.intermsubm1_2.repository.AuthRepository
import com.dicoding.picodiploma.intermsubm1_2.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(val authRepository: AuthRepository): ViewModel() {

    val user = MutableLiveData<RegisterResponse>()

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> get() = _responseMessage


    fun getRegistry(name : String, email: String, password: String) {
        val client = authRepository.postRegistry(name, email, password)
        client.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    _responseMessage.postValue("Data berhasil disimpan")
                    user.value = response.body()

                }else{
                    _responseMessage.postValue("Gagal menyimpan data")
                    Log.d("Failure", response.message().toString())
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _responseMessage.postValue("Gagal menyimpan data")
                Log.d("Failure", t.message.toString())
            }

        })
    }

}