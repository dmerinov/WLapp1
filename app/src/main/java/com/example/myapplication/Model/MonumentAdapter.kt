package com.example.myapplication.Model

import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.ItemDetailActivity
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
            view.tvMonument.text = monument.name
            view.tvLatitude.text = monument.latitude
            view.tvLongitude.text = monument.longitude
            Picasso.get().load(monument.photo).into(view.iv_monument)
            view.setOnClickListener{
                Toast.makeText(view.context, "You've selected ${monument.name}",Toast.LENGTH_SHORT).show()
                //you have to provide intent with the context as within the listener the context get lost.
                //val intent = Intent(view.context, ItemDetailActivity::class.java)
                //intent.putExtra("EXTRA_MONUMENT", monument)
                //view.context.startActivity(intent)
            }

        }
    }

}




