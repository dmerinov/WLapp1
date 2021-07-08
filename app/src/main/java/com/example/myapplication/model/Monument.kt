package com.example.myapplication.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Monument(
    val name:String,
    val latitude:String,
    val longitude:String,
    val photo:String,
    val textDetail:String
) : Parcelable