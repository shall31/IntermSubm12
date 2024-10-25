package com.dicoding.picodiploma.intermsubm1_2.api

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.dicoding.picodiploma.intermsubm1_2.utils.PreferenceHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val token = PreferenceHelper().getToken()
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer $token")
                        .method(original.method, original.body)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://story-api.dicoding.dev/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }

    }

}