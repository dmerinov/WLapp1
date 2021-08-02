package com.example.myapplication.data.preferences

interface Preferences {
    fun addFavouriteMonument(id: String)
    fun removeFavouriteMonument(id: String)
    fun getFavourites(): Set<String>
}