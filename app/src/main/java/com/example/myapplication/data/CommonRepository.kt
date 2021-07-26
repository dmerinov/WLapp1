package com.example.myapplication.data

import com.example.myapplication.data.local.Local
import com.example.myapplication.data.network.Network
import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto

class CommonRepository(private val network: Network, private val local: Local) : Repository {
    override fun getMonuments(success: (List<MonumentDto>) -> Unit, error: () -> Unit) {

        local.getMonuments(
            success = {
                println("list recovered successfully from local")
                success(it)
            },
            error = {
                println("couldn't load data from local, switching to network")
                network.getMonuments(
                    success = {
                        local.saveMonuments(it)
                        success(it)
                    },
                    error = {
                        error()
                    })
            }
        )

    }

    override fun getDetailMonument(
        id: String,
        success: (MonumentDetailDto) -> Unit,
        error: () -> Unit
    ) {
        local.getDetailMonument(id,
            success = {
                success(it)
            }, error = {
                println("couldn't load detail data from local, switching to network")
                network.getDetailMonument(id,
                    success = {
                        local.saveDetailMonument(it)
                        success(it)
                    },
                    error = {
                        error()
                    })
            })
    }
}