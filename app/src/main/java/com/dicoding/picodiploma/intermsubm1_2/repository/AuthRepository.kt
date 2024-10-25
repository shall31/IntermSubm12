package com.dicoding.picodiploma.intermsubm1_2.repository

import com.dicoding.picodiploma.intermsubm1_2.api.ApiConfig
import com.dicoding.picodiploma.intermsubm1_2.api.ApiService
import com.dicoding.picodiploma.intermsubm1_2.response.DetailResponse
import com.dicoding.picodiploma.intermsubm1_2.response.LoginResponse
import com.dicoding.picodiploma.intermsubm1_2.response.RegisterResponse
import com.dicoding.picodiploma.intermsubm1_2.response.StoriesResponse
import retrofit2.Call
//
class AuthRepository(private val apiService: ApiService) {

    fun postRegistry(name: String, email: String, password: String): Call<RegisterResponse> {
        return apiService.getRegistry(name, email, password)
    }

    fun postLogin(email: String, password: String): Call<LoginResponse> {
        return apiService.getLogin(email, password)
    }
//
//    fun postStories(): Call<StoriesResponse> {
//        return apiService.getStories()
//    }
//
    fun postDetail(id: String): Call<DetailResponse> {
        return  apiService.getDetail(id)
    }

    fun postStoriesWithLoc(location: Int): Call<StoriesResponse> {
        return apiService.getStoriesWithLoc(location)
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null

        fun getInstance(): AuthRepository {
            return instance ?: synchronized(AuthRepository::class.java){
                if (instance == null){
                    instance = AuthRepository(ApiConfig.getApiService())
                }
                return instance as AuthRepository
            }
        }
    }
//
}