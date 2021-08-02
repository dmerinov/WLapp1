package com.example.myapplication.presenter

import com.example.myapplication.data.Repository
import com.example.myapplication.model.MonumentDomainModel
import com.example.myapplication.model.MonumentDto

class MonumentPresenter(
    private val view: MonumentView,
    private val repository: Repository
) {

    fun initialize() {
        getMonuments()
    }

    fun getMonuments() {
        repository.getMonuments(
            success = {
                view.showMonuments(it)
            },
            error = {
                println("error getting the response")
            })
    }

    fun onMonumentClick(monumentDM: MonumentDomainModel) {
        view.navigateToMonumentDetailScreen(monumentDM)
    }

}

interface MonumentView {
    fun showMonuments(monuments: List<MonumentDomainModel>)
    fun navigateToMonumentDetailScreen(monumentDM: MonumentDomainModel)
}