package com.example.myapplication.data

import com.example.myapplication.data.network.Network
import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto

class CommonRepository(private val network: Network) : Repository {
    override fun getMonuments(success: (List<MonumentDto>) -> Unit, error: () -> Unit) {
        network.getMonuments(
            success = {
                success(it)
            },
            error = {
                error()
            })
    }

    override fun getDetailMonument(
        id: String,
        success: (MonumentDetailDto) -> Unit,
        error: () -> Unit
    ) {
        TODO("Not yet implemented")
    }
}