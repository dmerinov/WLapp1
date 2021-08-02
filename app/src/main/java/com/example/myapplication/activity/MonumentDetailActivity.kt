package com.example.myapplication.activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.CommonRepository
import com.example.myapplication.data.db.RealmDataSource
import com.example.myapplication.data.network.NetworkDataSource
import com.example.myapplication.data.preferences.CommonPreferences
import com.example.myapplication.model.MonumentDomainDetailModel
import com.example.myapplication.presenter.MonumentDetailPresenter
import com.example.myapplication.presenter.MonumentDetailView
import kotlinx.android.synthetic.main.activity_monument_detail.*


class MonumentDetailActivity : AppCompatActivity(), MonumentDetailView {

    private lateinit var presenter: MonumentDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monument_detail)
        initializeUI()
        MonumentDetailPresenter(
            this,
            repository = CommonRepository(
                network = NetworkDataSource(),
                prefs = CommonPreferences(this@MonumentDetailActivity),
                realm = RealmDataSource(this@MonumentDetailActivity)
            )
        ).also { presenter = it }
        presenter.initialize()
        registerListeners()
    }

    private fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun registerListeners() {
        isFavourite.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.onFavouriteClicked(isChecked)
        }
    }
    
    override fun getMonumentId(): String = intent.getStringExtra("RECEIVING_MONUMENT_ID")
        ?: throw IllegalArgumentException("Activity needs a monument id")

    override fun showMonument(mMonumentDto: MonumentDomainDetailModel) {
        titleDetail.text = mMonumentDto.title
        address.text = mMonumentDto.address
        email.text = mMonumentDto.email
        contact.text = mMonumentDto.phone
        monumentDescript.text = mMonumentDto.description
        monumentDescript.movementMethod = ScrollingMovementMethod()
        isFavourite.isChecked = mMonumentDto.isFavourite
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}