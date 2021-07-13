package com.example.myapplication.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Monument(
    @SerializedName("id")val id: Int,
    @SerializedName("title")val name: String,
    @SerializedName("address")val address: String,
    @SerializedName("transport")val transport: String,
    @SerializedName("email") val email: String,
    @SerializedName("geocoordinates")val geocoordinates: String,
    @SerializedName("description")val description: String,
    @SerializedName("phone")val phone:String
) : Parcelable