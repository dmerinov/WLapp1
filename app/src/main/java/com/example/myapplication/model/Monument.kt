package com.example.myapplication.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Monument(
    val id: Int,
    val name: String,
    val address: String,
    val geocoordinates: String,
    val description: String,
    val contact: String
) : Parcelable