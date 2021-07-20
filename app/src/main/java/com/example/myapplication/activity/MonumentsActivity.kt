package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.MonumentAdapter
import com.example.myapplication.databinding.ItemListElementBinding
import com.example.myapplication.model.MonumentDto
import com.example.myapplication.presenter.MonumentPresenter
import com.example.myapplication.presenter.MonumentView

class MonumentsActivity : AppCompatActivity(), MonumentView {

    private val monumentsAdapter: MonumentAdapter by lazy {
        MonumentAdapter() {
            navigateToMonumentDetailScreen(it) //TODO
        }
    }
    private val presenter = MonumentPresenter(this)
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

    override fun navigateToMonumentDetailScreen(monumentDto: MonumentDto) {
        val intent = Intent(this, MonumentDetailActivity::class.java)
        intent.putExtra("RECEIVING_MONUMENT_ID", monumentDto.id)
        startActivity(intent)
    }

    override fun getAdapter(): MonumentAdapter {
        return monumentsAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}



