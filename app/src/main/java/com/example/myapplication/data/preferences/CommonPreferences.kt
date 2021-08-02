package com.example.myapplication.data.preferences

import android.content.Context

class CommonPreferences(context: Context) : Preferences {

    companion object {
        private const val FAVOURITE_MONUMENTS_KEY = "FAVOURITE_MONUMENTS_KEY"
    }

    private val preferences = context.getSharedPreferences("default", 0)

    override fun addFavouriteMonument(id: String) {
        val favourites = getFavourites().toMutableSet()
        favourites.add(id)
        putString(FAVOURITE_MONUMENTS_KEY, favourites.joinToString(","))
    }

    override fun removeFavouriteMonument(id: String) {
        val favourites = getFavourites().toMutableSet()
        favourites.remove(id)
        putString(FAVOURITE_MONUMENTS_KEY, favourites.joinToString(","))
    }

    override fun getFavourites(): Set<String> {
        val value = getString(FAVOURITE_MONUMENTS_KEY)
        return if (value.isNotEmpty()) {
            value.split(",").toSet()
        } else {
            emptySet()
        }
    }

    private fun putString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    private fun getString(key: String): String {
        return preferences.getString(key, "") ?: ""
    }
}