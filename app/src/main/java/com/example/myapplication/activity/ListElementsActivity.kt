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
import com.example.myapplication.model.Monument
import com.example.myapplication.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_list_element.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListElementsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_list_element)

        //val staticMonument = Monument(1,"nombre","direccion","123.123,234.234","desc","123456789")

        var Monuments: MutableList<Monument>? = mutableListOf<Monument>()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        service.getAllPoints().enqueue(object: Callback<MutableList<Monument>> {
            override fun onResponse(
                call: Call<MutableList<Monument>>,
                response: Response<MutableList<Monument>>
            ) {
                Monuments = (response.body()?.toMutableList())
                Log.i("TAG_LOGS", Gson().toJson(Monuments))
                Log.i("TAG_LOGS", "Api reached")
                initRecycler(Monuments)
            }

            override fun onFailure(call: Call<MutableList<Monument>>, t: Throwable) {
               t?.printStackTrace()
                onAPIFailure()
            }

        })

    }
    private fun onAPIFailure(){
        Toast.makeText(this,"Can't reach the APIs info",Toast.LENGTH_SHORT)
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

    fun initRecycler(recoveredMonuments:MutableList<Monument>?) {
        val staticMonument = Monument(1,"nombre","direccion","123.123,234.234","desc","123456789")
        rvMonument.layoutManager = LinearLayoutManager(this)
        recoveredMonuments?.add(staticMonument)
        Log.i("tamaño",recoveredMonuments.toString())
        val adapter = recoveredMonuments?.let { MonumentAdapter(it) }
        rvMonument.adapter = adapter
    }
}



