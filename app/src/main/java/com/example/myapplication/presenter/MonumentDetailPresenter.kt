package com.example.myapplication.presenter

import com.example.myapplication.data.Repository
import com.example.myapplication.model.MonumentDomainDetailModel

class MonumentDetailPresenter(
    private val view: MonumentDetailView,
    private val repository: Repository
) {

    fun initialize() {
        getMonumentInfo()
    }

    private fun getMonumentInfo() {

        repository.getDetailMonument(view.getMonumentId(),
            success = {
                view.showMonument(it)
            },
            error = {
                println("error while recovering the detailed monument")
            }
        )
    }

    fun onFavouriteClicked(isChecked: Boolean) {
        if (isChecked) {
            addFavourite(view.getMonumentId())
        } else {
            removeFavourite(view.getMonumentId())

        }

    }

    private fun removeFavourite(id: String) {
        repository.removeFavourite(id)
    }

    private fun addFavourite(id: String) {
        repository.addFavourite(id)
    }
}

interface MonumentDetailView {
    fun getMonumentId(): String
    fun showMonument(mMonumentDto: MonumentDomainDetailModel)
}