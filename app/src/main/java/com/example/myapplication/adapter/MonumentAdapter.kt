package com.example.myapplication.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activity.ItemDetailActivity
import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto
import com.example.myapplication.retrofit.ApiService
import com.example.myapplication.retrofit.RetrofitResource
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_monument.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonumentAdapter(val items: MutableList<MonumentDto> = mutableListOf()) :

    RecyclerView.Adapter<MonumentAdapter.MonumentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonumentHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MonumentHolder(layoutInflater.inflate(R.layout.item_monument, parent, false))
    }

    //draws an item
    override fun onBindViewHolder(holder: MonumentHolder, position: Int) {
        holder.render(items[position])
    }

    //returns how many element the list has
    override fun getItemCount(): Int = items.size

    fun replaceAll(newItems: List<MonumentDto>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class MonumentHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(monument: MonumentDto) {
            view.Monument.text = monument.id + " - " + monument.title
            view.Latitude.text = monument.geocoordinates

            view.setOnClickListener {


                //you have to provide intent with the context as within the listener the context get lost.
                val intent = Intent(view.context, ItemDetailActivity::class.java)
                intent.putExtra("RECEIVING_MONUMENT_ID", monument.id)
                view.context.startActivity(intent)
            }

        }

    }

}




