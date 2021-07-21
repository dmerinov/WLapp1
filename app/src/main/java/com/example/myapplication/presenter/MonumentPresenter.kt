package com.example.myapplication.presenter

import com.example.myapplication.model.MonumentDto
import com.example.myapplication.repository.SourceRepository

class MonumentPresenter(
    private val view: MonumentView,
    private val repository: SourceRepository
) {

    fun initialize() {
        getMonuments()
    }

    private fun getMonuments() {
        repository.getMonuments(
            success = {
                view.showMonuments(it)
            },
            error = {
                println("error getting the response")
            }
        )
    }

}

interface MonumentView {
    fun showMonuments(monuments: List<MonumentDto>)
    fun navigateToMonumentDetailScreen(monumentDto: MonumentDto)
}