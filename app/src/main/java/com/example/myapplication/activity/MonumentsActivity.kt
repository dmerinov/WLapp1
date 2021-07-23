package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.MonumentAdapter
import com.example.myapplication.data.CommonRepository
import com.example.myapplication.data.local.LocalDataSource
import com.example.myapplication.data.network.NetworkDataSource
import com.example.myapplication.databinding.ItemListElementBinding
import com.example.myapplication.model.MonumentDto
import com.example.myapplication.presenter.MonumentPresenter
import com.example.myapplication.presenter.MonumentView

class MonumentsActivity : AppCompatActivity(), MonumentView {

    private val presenter: MonumentPresenter by lazy {
        MonumentPresenter(
            this,
            repository = CommonRepository(
                network = NetworkDataSource(),
                local = LocalDataSource(this@MonumentsActivity)
            )
        )
    }
    private val monumentsAdapter: MonumentAdapter by lazy {
        MonumentAdapter() {
            presenter.onMonumentClick(it)
        }
    }

    private lateinit var binding: ItemListElementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemListElementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()
        presenter.initialize()
        registerListener()
    }

    private fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        with(binding.rvMonument) {
            layoutManager = LinearLayoutManager(this@MonumentsActivity)
            adapter = monumentsAdapter
        }
    }

    private fun registerListener() {
        //nothing to do
    }

    override fun showMonuments(monuments: List<MonumentDto>) {
        monumentsAdapter.replaceAll(monuments)
    }

    override fun navigateToMonumentDetailScreen(monumentDto: MonumentDto) {
        val intent = Intent(this, MonumentDetailActivity::class.java)
        intent.putExtra("RECEIVING_MONUMENT_ID", monumentDto.id)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}