package com.example.myapplication.retrofit
import com.example.myapplication.model.Monument
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts/")
    fun getAllPoints(): Call<MutableList<Monument>>

    @GET("points/{id}")
    fun getPostById(@Path("id") id: Int): Call<Monument>

}