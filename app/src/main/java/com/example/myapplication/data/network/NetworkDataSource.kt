package com.example.myapplication.data.network

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto
import com.example.myapplication.model.MonumentResponse
import com.example.myapplication.retrofit.ApiService
import com.example.myapplication.retrofit.RetrofitResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkDataSource: Network{

    private val service: ApiService = RetrofitResource.getRetrofit().create(ApiService::class.java)

    override fun getMonuments(success: (List<MonumentDto>) -> Unit, error: () -> Unit) {

        val result: Call<MonumentResponse> = service.getAllPoints()
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

    override fun getDetailMonument(
        id: String,
        success: (MonumentDetailDto) -> Unit,
        error: () -> Unit
    ) {

        val result: Call<MonumentDetailDto> = service.getPostById(id)

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