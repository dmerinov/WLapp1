package com.example.myapplication.data

import com.example.myapplication.data.local.Local
import com.example.myapplication.data.network.Network
import com.example.myapplication.data.realm.Realm
import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto

class CommonRepository(private val network: Network, private val local: Local) : Repository {
    override fun getMonuments(success: (List<MonumentDto>) -> Unit, error: () -> Unit) {

        network.getMonuments(
            success = {
                local.setMonuments(it)
                success(it)
            },
            error = {
                if (local.hasMonuments()) {
                    val monuments = local.getMonuments()
                    if (monuments.isNotEmpty()) {
                        success(monuments)
                    } else {
                        error()
                    }
                } else {
                    error()
                }

            }
        )
    }

    override fun getDetailMonument(
        id: String,
        success: (MonumentDetailDto) -> Unit,
        error: () -> Unit
    ) {

        network.getDetailMonument(
            id = id,
            success = {
                local.setMonumentDetail(it)
                success(it)
            },
            error = {
                if (local.hasMonumentDetail(id)) {
                    val detailMonument = local.getMonumentDetail(id)
                    success(detailMonument)
                } else {
                    error()
                }

            }
        )
    }
}