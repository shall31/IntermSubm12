package com.dicoding.picodiploma.intermsubm1_2.api

import com.dicoding.picodiploma.intermsubm1_2.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun getRegistry(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun getLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("stories")
    @Headers("Authorization: Bearer")
    suspend fun getQuote(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): StoriesResponse

    @GET("stories")
    @Headers("Authorization: Bearer")
    fun getStoriesWithLoc(
        @Query("location") location: Int
    ): Call<StoriesResponse>

    @GET("stories/{id}")
    @Headers("Authorization: Bearer")
    fun getDetail(
        @Path("id") id: String
    ): Call<DetailResponse>

    @GET("stories")
    @Headers("Authorization: Bearer")
    fun getStories(): Call<StoriesResponse>

    @Multipart
    @POST("stories")
    @Headers("Authorization: Bearer")
    fun getAddStory(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lat") lat: RequestBody,
        @Part("lon") lon: RequestBody
    ): Call<RegisterResponse> //sengaja pake registerresponse karena sama



}

