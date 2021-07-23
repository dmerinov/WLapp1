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

        // repository.getMonumentInfo(view.getMonumentId())
    }


}

interface MonumentDetailView {
    fun getMonumentId(): String
    fun showMonument(mMonumentDto: MonumentDetailDto)
}