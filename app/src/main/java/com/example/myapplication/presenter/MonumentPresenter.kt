package com.example.myapplication.presenter

import com.example.myapplication.adapter.MonumentAdapter
import com.example.myapplication.model.MonumentDto
import com.example.myapplication.repository.RepositoryInterfaceAllMonuments
import com.example.myapplication.repository.SourceRepository

class MonumentPresenter(private val view: MonumentView) : RepositoryInterfaceAllMonuments {


    fun initialize() {
        getMonuments()
    }

    override fun getMonuments() {
        SourceRepository.getMonuments(view)
    }

}

interface MonumentView {
    fun getAdapter(): MonumentAdapter
    fun navigateToMonumentDetailScreen(monumentDto: MonumentDto)
}