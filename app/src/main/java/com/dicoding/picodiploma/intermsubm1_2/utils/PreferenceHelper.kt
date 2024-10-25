package com.dicoding.picodiploma.intermsubm1_2.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {

    private val sharedPreferences: SharedPreferences =
        MyApplication.appContext.getSharedPreferences("StoryPrefs", Context.MODE_PRIVATE)


    fun saveToken(token: String){
        sharedPreferences.edit().putString("TOKEN_KEY", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("TOKEN_KEY", null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove("TOKEN_KEY").apply()
    }

}