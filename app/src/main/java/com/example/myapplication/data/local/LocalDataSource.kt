package com.example.myapplication.data.local

import android.content.Context
import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDto
import com.google.gson.Gson

class LocalDataSource(val context: Context) : Local {

    val PREFS = "DB_PREFERENCES"
    private val MONUMENTLIST = "MONUMENT_LIST"
    val storage = context.getSharedPreferences(PREFS, 0)


    override fun getMonuments(success: (List<MonumentDto>) -> Unit, error: () -> Unit) {
        if (storage.contains(MONUMENTLIST)) {
            success(
                getSavedMonuments()
            )
        } else {
            error()
        }
    }

    override fun getDetailMonument(
        id: String,
        success: (MonumentDetailDto) -> Unit,
        error: () -> Unit
    ) {
        if (storage.contains(MONUMENTLIST)) {
            success(
                getSavedMonumentByID(id)
            )
        } else {
            error()
        }
    }

    override fun saveMonuments(monuments: List<MonumentDto>) {

        val gson = Gson()
        val jsonString = gson.toJson(monuments)
        storage.edit().putString(MONUMENTLIST, jsonString).apply()
        println("configuration saved successfully")
    }

    override fun saveDetailMonument(monument: MonumentDetailDto) {
        val gson = Gson()
        val jsonString = gson.toJson(monument)
        storage.edit().putString(MONUMENTLIST + "${monument.id}", jsonString).apply()
        println("detail configuration saved successfully")
    }

    private fun getSavedMonuments(): List<MonumentDto> {

        val jsonContent = storage.getString(MONUMENTLIST, "")
        val gson = Gson()
        var list: List<MonumentDto> = emptyList()
        if (jsonContent.equals("")) {
            println("THE MONUMENT LIST IS EMPTY")
            //throw exception
        } else {
            list = gson.fromJson(jsonContent, Array<MonumentDto>::class.java).toList()
            println("list returned from local")
        }
        return list
    }

    private fun getSavedMonumentByID(id: String): MonumentDetailDto {
        val jsonContent = storage.getString(MONUMENTLIST + "${id}", "")
        val gson = Gson()
        var monument: MonumentDetailDto = gson.fromJson(jsonContent, MonumentDetailDto::class.java)
        println("monument returned from local")
        return monument
    }
}