package com.example.myapplication.activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.model.Monument
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*


class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        processItem()
    }

    private fun processItem() {
        val objectMonument = getContent()
        if (objectMonument != null) {
            paintItem(objectMonument)
        }
    }

    private fun getContent(): Monument? {
        val gson = Gson()
        val jsonMonument = intent.getSerializableExtra("EXTRA_MONUMENT") as String
        return gson.fromJson(jsonMonument, Monument::class.java)
    }

    private fun paintItem(objectMonument: Monument) {
        tvMonumentDesc.text = objectMonument.textDetail
        tvMonumentDesc.movementMethod = ScrollingMovementMethod()
        imageViewItemDetail.loadUrl(objectMonument.photo)
    }

    private fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(this)
    }
}