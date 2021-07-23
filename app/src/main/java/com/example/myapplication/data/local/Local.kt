package com.example.myapplication.data.local

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto

interface Local {
    fun getMonuments(success: (List<MonumentDto>) -> Unit, error: () -> Unit)
    fun getDetailMonument(id: String, success: (MonumentDetailDto) -> Unit, error: () -> Unit)
    fun saveMonuments(monutments: List<MonumentDto>)
    fun saveDetailMonument(monument: MonumentDetailDto)
}