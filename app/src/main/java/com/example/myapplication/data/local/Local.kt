package com.example.myapplication.data.local

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto

interface Local {
    fun hasMonuments(): Boolean
    fun getMonuments(): List<MonumentDto>
    fun setMonuments(monuments: List<MonumentDto>)

    fun hasMonumentDetail(id: String): Boolean
    fun getMonumentDetail(id: String): MonumentDetailDto
    fun setMonumentDetail(monument: MonumentDetailDto)
}