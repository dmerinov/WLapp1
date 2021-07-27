package com.example.myapplication.data.realm

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto

interface Realm {
    fun hasMonuments(): Boolean
    fun getMonuments(): List<MonumentDto>
    fun setMonuments(monuments: List<MonumentDto>)

    fun hasMonumentDetail(id: String): Boolean
    fun getMonumentDetail(id: String): MonumentDetailDto
    fun setMonumentDetail(monument: MonumentDetailDto)
}