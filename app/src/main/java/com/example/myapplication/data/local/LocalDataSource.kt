package com.example.myapplication.data.local

import android.content.Context
import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto
import com.google.gson.Gson

class LocalDataSource(context: Context) : Local {

    companion object {
        private const val MONUMENTS_LIST_KEY = "MONUMENTS_LIST_KEY"
        private const val MONUMENT_DETAIL_KEY= "MONUMENT_DETAIL_KEY"

        private fun getMonumentDetailKey(id:String)= "${MONUMENT_DETAIL_KEY}_$id"
    }

    private val preferences = context.getSharedPreferences("default", 0)
    private val gson = Gson()

    override fun hasMonuments(): Boolean = hasKey(MONUMENTS_LIST_KEY)

    override fun getMonuments(): List<MonumentDto> {
        val json = getString(MONUMENTS_LIST_KEY)
        return gson.fromJson(json, Array<MonumentDto>::class.java).toList()
    }

    override fun setMonuments(monuments: List<MonumentDto>) {
        putString(MONUMENTS_LIST_KEY,gson.toJson(monuments))
    }

    override fun hasMonumentDetail(id: String): Boolean = hasKey(getMonumentDetailKey(id))

    override fun getMonumentDetail(id: String): MonumentDetailDto {
        val json = getString(getMonumentDetailKey(id))
        return gson.fromJson(json,MonumentDetailDto::class.java)
    }

    override fun setMonumentDetail(monument: MonumentDetailDto) {
        putString(getMonumentDetailKey(monument.id),gson.toJson(monument))
    }

    private fun hasKey(key: String): Boolean {
        return preferences.contains(key)
    }

    private fun putString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    private fun getString(key: String): String {
        return preferences.getString(key, "") ?: ""
    }

}