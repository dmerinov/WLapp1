package com.example.myapplication.activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto
import com.example.myapplication.retrofit.ApiService
import com.example.myapplication.retrofit.RetrofitResource
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_item_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        initializeUI()
        processItem()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun processItem() {
        val idReturned = intent.getSerializableExtra("RECEIVING_MONUMENT_ID") as String
        searchByID(idReturned)
    }

    private fun paintItem(objectMonument: MonumentDetailDto) {
        tvMonumentDesc.text = objectMonument.description
        tvMonumentDesc.movementMethod = ScrollingMovementMethod()
    }

    private fun searchByID(query: String) {
        val userService: ApiService =
            RetrofitResource.getRetrofit().create(ApiService::class.java)
        val result: Call<MonumentDetailDto> = userService.getPostById("$query")
        lateinit var returnedObject:MonumentDetailDto

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
                    if (response.body() != null)
                        paintItem(response.body()!!)


                }
            }
        })
    }

}