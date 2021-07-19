package com.example.myapplication.activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.retrofit.ApiService
import com.example.myapplication.retrofit.RetrofitResource
import kotlinx.android.synthetic.main.activity_item_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MonumentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        initializeUI()
        processItem()
    }

    private fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun processItem() {
        val idReturned = intent.getSerializableExtra("RECEIVING_MONUMENT_ID") as String
        getMonumentByID(idReturned)
    }

    private fun showMonument(monument: MonumentDetailDto) {
        titleDetail.text = monument.title
        address.text = monument.address
        email.text = monument.email
        contact.text = monument.phone
        monumentDescript.text = monument.description
        monumentDescript.movementMethod = ScrollingMovementMethod()
    }

    private fun getMonumentByID(query: String) {
        val userService: ApiService =
            RetrofitResource.getRetrofit().create(ApiService::class.java)
        val result: Call<MonumentDetailDto> = userService.getPostById("$query")

        result.enqueue(object : Callback<MonumentDetailDto> {
            override fun onFailure(call: Call<MonumentDetailDto>, t: Throwable) {
                Log.i("LOG_TAG", "error")
            }

            override fun onResponse(
                call: Call<MonumentDetailDto>,
                response: Response<MonumentDetailDto>
            ) {
                if (response?.isSuccessful) {
                    Log.i("LOG_TAG", "okey")
                    response.body()?.let { showMonument(it) }
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}