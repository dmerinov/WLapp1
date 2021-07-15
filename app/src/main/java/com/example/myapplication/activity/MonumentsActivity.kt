package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.MonumentAdapter
import com.example.myapplication.databinding.ItemListElementBinding
import com.example.myapplication.model.MonumentResponse
import com.example.myapplication.retrofit.ApiService
import com.example.myapplication.retrofit.RetrofitResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonumentsActivity : AppCompatActivity() {

    private lateinit var binding: ItemListElementBinding
    private val monumentsAdapter: MonumentAdapter by lazy { MonumentAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemListElementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUI()
        registerListener()
        getMonuments()
    }

    private fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        with(binding.rvMonument) {
            layoutManager = LinearLayoutManager(this@MonumentsActivity)
            adapter = monumentsAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun registerListener() {
        //nothing to do
    }

    private fun getMonuments() {
        val userService: ApiService = RetrofitResource.getRetrofit().create(ApiService::class.java)
        val result: Call<MonumentResponse> = userService.getAllPoints()

        result.enqueue(object : Callback<MonumentResponse> {
            override fun onFailure(call: Call<MonumentResponse>, t: Throwable) {
                Log.i("LOG_TAG", "error")
            }

            override fun onResponse(
                call: Call<MonumentResponse>,
                response: Response<MonumentResponse>
            ) {
                Log.i("LOG_TAG", "okey")
                response.body()?.let { monumentsAdapter.replaceAll(it.list) }
            }

        })
    }

}



