package com.example.myapplication.retrofit

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("points")
    fun getAllPoints(): Call<MonumentResponse>

    @GET("points/{id}")
    fun getPostById(@Path("id") id: String): Call<MonumentDetailDto>

}