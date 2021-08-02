package com.example.myapplication.data.db

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDetailVO
import com.example.myapplication.model.MonumentDto
import com.example.myapplication.model.MonumentVO

interface Local {
    fun hasMonuments(): Boolean
    fun getMonuments(): List<MonumentVO>
    fun setMonuments(monuments: List<MonumentDto>)

    fun hasMonumentDetail(id: String): Boolean
    fun getMonumentDetail(id: String): MonumentDetailVO
    fun setMonumentDetail(monument: MonumentDetailDto)
}