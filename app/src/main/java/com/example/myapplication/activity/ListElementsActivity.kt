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
import com.example.myapplication.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListElementsActivity : AppCompatActivity() {
    private lateinit var binding: ItemListElementBinding
    private lateinit var adapter: MonumentAdapter
    val MonumentList = mutableListOf<Monument>()
    val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val BASE_URL2 = "https://t21services.herokuapp.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemListElementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchAll() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getAllPoints()
            val monumental = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val objects = monumental ?: emptyList<Monument>()
                    MonumentList.clear()
                    MonumentList.addAll(objects as MutableList<Monument>)
                    adapter.notifyDataSetChanged()
                    Log.i("LOG_TAG","success")
                } else {
                    showError()
                    Log.i("LOG_TAG","err")
                }
            }

        }
    }

    private fun initRecyclerView() {
        adapter = MonumentAdapter(MonumentList)
        binding.rvMonument.layoutManager = LinearLayoutManager(this)
        binding.rvMonument.adapter = adapter
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getPostById("$query")
            val monumental = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val objects = monumental ?: emptyList<Monument>()
                    MonumentList.clear()
                    MonumentList.addAll(objects as MutableList<Monument>)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }

        }
    }

    private fun showError() {
        Toast.makeText(this, "An error has ocurred", Toast.LENGTH_SHORT).show()
    }

    private fun onAPIFailure() {
        Toast.makeText(this, "Can't reach the APIs info", Toast.LENGTH_SHORT)
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



