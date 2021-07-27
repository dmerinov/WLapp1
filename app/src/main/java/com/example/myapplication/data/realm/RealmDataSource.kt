package com.example.myapplication.data.realm

import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto

class RealmDataSource: Realm {
    override fun hasMonuments(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getMonuments(): List<MonumentDto> {
        TODO("Not yet implemented")
    }

    override fun setMonuments(monuments: List<MonumentDto>) {
        TODO("Not yet implemented")
    }

    override fun hasMonumentDetail(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getMonumentDetail(id: String): MonumentDetailDto {
        TODO("Not yet implemented")
    }

    override fun setMonumentDetail(monument: MonumentDetailDto) {
        TODO("Not yet implemented")
    }
}