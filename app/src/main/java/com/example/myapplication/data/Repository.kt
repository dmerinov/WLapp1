package com.example.myapplication.data

import com.example.myapplication.model.MonumentDomainDetailModel
import com.example.myapplication.model.MonumentDomainModel

interface Repository {
    fun getMonuments(success: (List<MonumentDomainModel>) -> Unit, error: () -> Unit)
    fun getDetailMonument(
        id: String,
        success: (MonumentDomainDetailModel) -> Unit,
        error: () -> Unit
    )
    fun addFavourite(id:String)
    fun removeFavourite(id:String)
}