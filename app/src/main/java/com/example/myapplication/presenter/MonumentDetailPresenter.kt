package com.example.myapplication.presenter

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.repository.SourceRepository

class MonumentDetailPresenter(
    private val view: MonumentDetailView,
    private val repository: SourceRepository
) {

    fun initialize() {
        getMonumentInfo()
    }

     private fun getMonumentInfo() {

        repository.getMonumentInfo(view.getMonumentId(),
            success = {
                view.showMonument(it)
            },
            error = {
                println("an error has occurred while trying to fetch the monument")
            }
        )
    }


}

interface MonumentDetailView {
    fun getMonumentId(): String
    fun showMonument(mMonumentDto: MonumentDetailDto)
}