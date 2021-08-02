package com.example.myapplication.model

data class MonumentDomainDetailModel(
    val id: String,
    val address: String,
    val description: String,
    val email: String,
    val geocoordinates: String,
    val phone: String,
    val title: String,
    val transport: String,
    var isFavourite: Boolean
)
data class MonumentDomainModel(
    val id:String,
    val geocoordinates: String,
    val title:String
)

