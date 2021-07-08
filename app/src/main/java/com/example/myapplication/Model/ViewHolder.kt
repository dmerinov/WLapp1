package com.example.myapplication.Model

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //first we bind layout references with this vals (they are vals as they don't need to change their values once they're assigned)

    val monumentName = view.findViewById(R.id.tvMonument) as TextView
    val latitude = view.findViewById(R.id.tvLatitude) as TextView
    val longitude = view.findViewById(R.id.tvLongitude) as TextView
    val avatar = view.findViewById(R.id.iv_monument) as ImageView

    //this is going to be called by onBindViewHolder() so it can fill the data. Afterwards, we call itemView, which is the view (cell)
    //that we're filling and assing it a setOnClickListener which is going to paint the monument name that we clicked.
    //Finally we paint the Image using the ImageView providing an URL
    fun bind(monument:Monument, context: Context){
        monumentName.text= monument.name
        latitude.text=monument.latitude.toString()
        longitude.text=monument.longitude.toString()
        itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context,monument.name, Toast.LENGTH_SHORT).show() })
        avatar.loadUrl(monument.photo)
    }
    fun ImageView.loadUrl(url: String){
        Picasso.get().load(url).into(this)
    }
}