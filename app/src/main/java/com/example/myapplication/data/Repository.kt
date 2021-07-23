package com.example.myapplication.data

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto

interface Repository {
    fun getMonuments(success: (List<MonumentDto>) -> Unit, error: () -> Unit)
    fun getDetailMonument(id: String, success: (MonumentDetailDto) -> Unit, error: () -> Unit)
}