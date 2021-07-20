package com.example.myapplication.repository

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentResponse
import com.example.myapplication.presenter.MonumentDetailView
import com.example.myapplication.presenter.MonumentView
import com.example.myapplication.retrofit.ApiService
import com.example.myapplication.retrofit.RetrofitResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SourceRepository {

    fun getMonuments(view: MonumentView) {
        val userService: ApiService = RetrofitResource.getRetrofit().create(ApiService::class.java)
        val result: Call<MonumentResponse> = userService.getAllPoints()

        result.enqueue(object : Callback<MonumentResponse> {
            override fun onFailure(call: Call<MonumentResponse>, t: Throwable) {
                println("error")
            }

            override fun onResponse(
                call: Call<MonumentResponse>,
                response: Response<MonumentResponse>
            ) {
                response.body()?.let { view.getAdapter().replaceAll(it.list) }
            }

        })
    }

    fun getMonumentInfo(view: MonumentDetailView) {
        val userService: ApiService =
            RetrofitResource.getRetrofit().create(ApiService::class.java)
        val result: Call<MonumentDetailDto> = userService.getPostById(view.getMonumentId())

        result.enqueue(object : Callback<MonumentDetailDto> {
            override fun onFailure(call: Call<MonumentDetailDto>, t: Throwable) {
                println("error")
            }

            override fun onResponse(
                call: Call<MonumentDetailDto>,
                response: Response<MonumentDetailDto>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { view.showMonument(it) }
                }
            }
        })
    }

}

interface RepositoryInterfaceAllMonuments {
    fun getMonuments()
}

interface RepositoryInterfaceOneMonument {
    fun getMonumentInfo()
}