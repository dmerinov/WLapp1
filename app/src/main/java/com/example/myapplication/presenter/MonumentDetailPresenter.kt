package com.example.myapplication.presenter

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.repository.RepositoryInterfaceOneMonument
import com.example.myapplication.repository.SourceRepository

class MonumentDetailPresenter(private val view: MonumentDetailView) :
    RepositoryInterfaceOneMonument {

    fun initialize() {
        getMonumentInfo()
    }

    override fun getMonumentInfo() {

        SourceRepository.getMonumentInfo(view)
    }


}

interface MonumentDetailView {
    fun getMonumentId(): String
    fun showMonument(mMonumentDto: MonumentDetailDto)
}