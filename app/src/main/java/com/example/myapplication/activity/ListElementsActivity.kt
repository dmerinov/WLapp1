package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.MonumentAdapter
import com.example.myapplication.databinding.ItemListElementBinding
import com.example.myapplication.model.Monument
import com.example.myapplication.model.MonumentResponse
import com.example.myapplication.retrofit.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListElementsActivity : AppCompatActivity() {
    private lateinit var binding: ItemListElementBinding
    private lateinit var adapter: MonumentAdapter
    private var MonumentList: MutableList<Monument> = mutableListOf()
    val BASE_URL2 = "https://t21services.herokuapp.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemListElementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        searchAll()
    }

    private fun getRetrofit(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }


    private fun searchAll() {
        val userService: ApiService = getRetrofit().create(ApiService::class.java)
        val result: Call<MonumentResponse> = userService.getAllPoints()

        result.enqueue(object : Callback<MonumentResponse> {
            override fun onFailure(call: Call<MonumentResponse>, t: Throwable) {
                Log.i("LOG_TAG", "error")
                showError()
            }

            override fun onResponse(
                call: Call<MonumentResponse>,
                response: Response<MonumentResponse>
            ) {
                showOk()
                Log.i("LOG_TAG", "okey")
                response.body()?.let { MonumentList.addAll(it.list) }
                if (MonumentList != null)
                    adapter.notifyDataSetChanged()
            }

        })
    }

    private fun initRecyclerView() {
        adapter = MonumentAdapter(MonumentList)
        binding.rvMonument.layoutManager = LinearLayoutManager(this)
        binding.rvMonument.adapter = adapter
    }

    private fun showError() {
        Toast.makeText(this, "An error has ocurred", Toast.LENGTH_SHORT).show()
    }

    private fun showOk() {
        Toast.makeText(this, "OKEY", Toast.LENGTH_SHORT).show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_go_back -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}



