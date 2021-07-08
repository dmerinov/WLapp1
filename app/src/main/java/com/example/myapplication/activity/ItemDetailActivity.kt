package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.model.Monument
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        var gson=Gson()
        val jsonMonument = intent.getSerializableExtra("EXTRA_MONUMENT") as String
        val objectMonument = gson.fromJson(jsonMonument,Monument::class.java)
        var ivMonument = findViewById(R.id.imageViewItemDetail) as ImageView
        var monumentDesc = findViewById(R.id.tvMonumentDesc) as TextView
        monumentDesc.setText(objectMonument.textDetail)
        ivMonument.loadUrl(objectMonument.photo)
        Toast.makeText(this, "this is the ${objectMonument.name} detail view", Toast.LENGTH_SHORT).show()
    }

    fun ImageView.loadUrl(url: String){
        Picasso.get().load(url).into(this)
    }
}