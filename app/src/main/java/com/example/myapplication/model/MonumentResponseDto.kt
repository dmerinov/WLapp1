package com.example.myapplication.model
import com.google.gson.annotations.SerializedName

data class MonumentResponse(
    @SerializedName("list") val list: List<MonumentDto>
)

data class MonumentDto(
    @SerializedName("geocoordinates") val geocoordinates: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String
)

data class MonumentDetailDto(
    @SerializedName("address") val address: String,
    @SerializedName("description") val description: String,
    @SerializedName("email") val email: String,
    @SerializedName("geocoordinates") val geocoordinates: String,
    @SerializedName("id") val id: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("title") val title: String,
    @SerializedName("transport") val transport: String
)
