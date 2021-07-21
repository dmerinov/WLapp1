package com.example.myapplication.repository

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto
import com.example.myapplication.model.MonumentResponse
import com.example.myapplication.presenter.MonumentDetailView
import com.example.myapplication.presenter.MonumentView
import com.example.myapplication.retrofit.ApiService
import com.example.myapplication.retrofit.RetrofitResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SourceRepository {

    fun getMonuments(success: (List<MonumentDto>) -> Unit, error: ()-> Unit) {
        val userService: ApiService = RetrofitResource.getRetrofit().create(ApiService::class.java)
        val result: Call<MonumentResponse> = userService.getAllPoints()

        result.enqueue(object : Callback<MonumentResponse> {
            override fun onFailure(call: Call<MonumentResponse>, t: Throwable) {
                error()
            }

            override fun onResponse(
                call: Call<MonumentResponse>,
                response: Response<MonumentResponse>
            ) {
                response.body()?.let { success(it.list) }
            }

        })
    }

    fun getMonumentInfo(monumentID: String,success: (MonumentDetailDto) -> Unit,error: () -> Unit) {
        val userService: ApiService =
            RetrofitResource.getRetrofit().create(ApiService::class.java)
        val result: Call<MonumentDetailDto> = userService.getPostById(monumentID)

        result.enqueue(object : Callback<MonumentDetailDto> {
            override fun onFailure(call: Call<MonumentDetailDto>, t: Throwable) {
                error()
            }

            override fun onResponse(
                call: Call<MonumentDetailDto>,
                response: Response<MonumentDetailDto>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { success(it) }
                }
            }
        })
    }

}