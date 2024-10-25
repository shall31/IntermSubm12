package com.dicoding.picodiploma.intermsubm1_2.utils

import android.content.Context
import com.dicoding.picodiploma.intermsubm1_2.api.ApiConfig
import com.dicoding.picodiploma.intermsubm1_2.repository.MainRepository

object Injection {
    fun provideRepository(context: Context): MainRepository {
        val apiService = ApiConfig.getApiService()
        return MainRepository(apiService)
    }
}