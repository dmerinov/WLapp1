package com.example.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activity.ItemDetailActivity
import com.example.myapplication.model.Monument
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_monument.view.*

class MonumentAdapter(val monument: MutableList<Monument>) :

    RecyclerView.Adapter<MonumentAdapter.MonumentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonumentHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MonumentHolder(layoutInflater.inflate(R.layout.item_monument, parent, false))
    }

    //draws an item
    override fun onBindViewHolder(holder: MonumentHolder, position: Int) {
        holder.render(monument[position])
    }

    //returns how many element the list has
    override fun getItemCount(): Int = monument.size

    class MonumentHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(monument: Monument) {
            view.Monument.text = monument.name
            view.Latitude.text = monument.geocoordinates

            view.setOnClickListener {
                //you have to provide intent with the context as within the listener the context get lost.
                val intent = Intent(view.context, ItemDetailActivity::class.java)
                val gson = Gson()
                val intentData = gson.toJson(monument)
                intent.putExtra("EXTRA_MONUMENT", intentData)
                view.context.startActivity(intent)
            }

        }
    }

}




