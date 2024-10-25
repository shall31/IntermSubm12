package com.dicoding.picodiploma.intermsubm1_2.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.intermsubm1_2.repository.AuthRepository
import com.dicoding.picodiploma.intermsubm1_2.response.LoginResponse
import com.dicoding.picodiploma.intermsubm1_2.response.LoginResult
import com.dicoding.picodiploma.intermsubm1_2.utils.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(val authRepository: AuthRepository): ViewModel() {

    val login = MutableLiveData<LoginResult>()

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> get() = _responseMessage

    val preferenceHelper = PreferenceHelper()

    fun getLogin(email: String, password: String){
        val client = authRepository.postLogin(email, password)
        client.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    _responseMessage.postValue("Berhasil Login !")
                    login.value = response.body()?.loginResult

                    //menyimpan token ke dalam preference
                    preferenceHelper.saveToken(login.value!!.token )

                }else{
                    _responseMessage.postValue("Gagal Login ngab")
                    Log.d("Failure", response.message().toString())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _responseMessage.postValue("Gagal Login")
                Log.d("Failure", t.message.toString())
            }

        })
    }


}
