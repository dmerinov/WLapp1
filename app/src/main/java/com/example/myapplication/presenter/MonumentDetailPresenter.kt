package com.example.myapplication.presenter

import com.example.myapplication.data.Repository
import com.example.myapplication.model.MonumentDetailDto

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


}

interface MonumentDetailView {
    fun getMonumentId(): String
    fun showMonument(mMonumentDto: MonumentDetailDto)
}