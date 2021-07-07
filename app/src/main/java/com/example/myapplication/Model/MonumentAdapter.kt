package com.example.myapplication.Model

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_monument.view.*

class MonumentAdapter (val monument:List<Monument>) :RecyclerView.Adapter<MonumentAdapter.MonumentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonumentAdapter.MonumentHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MonumentHolder(layoutInflater.inflate(R.layout.item_monument,parent,false))
    }

    //cuantos items tiene el recycler view
    override fun onBindViewHolder(holder: MonumentHolder, position: Int) {
        holder.render(monument[position])
    }

    override fun getItemCount(): Int = monument.size

    class MonumentHolder (val view:View): RecyclerView.ViewHolder(view){
        fun render(monument:Monument){
            view.tvMonument.text = monument.Name
            view.tvLatitude.text = monument.Latitude
            view.tvLongitude.text = monument.Longitude
            Picasso.get().load(monument.photo).into(view.iv_monument)

        }
    }

}




