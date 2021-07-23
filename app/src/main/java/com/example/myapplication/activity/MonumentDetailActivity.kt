package com.example.myapplication.activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.presenter.MonumentDetailPresenter
import com.example.myapplication.presenter.MonumentDetailView
import com.example.myapplication.repository.NetworkRepository
import com.example.myapplication.repository.SourceRepository
import kotlinx.android.synthetic.main.activity_monument_detail.*


class MonumentDetailActivity : AppCompatActivity(), MonumentDetailView {

    private val presenter = MonumentDetailPresenter(
        this,
        SourceRepository(NetworkRepository())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monument_detail)
        initializeUI()
        presenter.initialize()
    }

    private fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getMonumentId(): String = intent.getStringExtra("RECEIVING_MONUMENT_ID")
        ?: throw IllegalArgumentException("Activity needs a monument id")

    override fun showMonument(mMonumentDto: MonumentDetailDto) {
        titleDetail.text = mMonumentDto.title
        address.text = mMonumentDto.address
        email.text = mMonumentDto.email
        contact.text = mMonumentDto.phone
        monumentDescript.text = mMonumentDto.description
        monumentDescript.movementMethod = ScrollingMovementMethod()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}