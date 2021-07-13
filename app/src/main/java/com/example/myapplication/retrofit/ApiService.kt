package com.example.myapplication.retrofit

import com.example.myapplication.model.Monument
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("points/")
   suspend fun getAllPoints(): Response<MutableList<Monument>>

    @GET("points/{id}")
  suspend  fun getPostById(@Path("id") id: String): Response<Monument>

}