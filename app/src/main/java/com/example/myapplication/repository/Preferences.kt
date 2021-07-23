package com.example.myapplication.repository

import android.content.Context
class Preferences(val context: Context) {

    private val SHARED_NAME ="db"

    val storage = context.getSharedPreferences(SHARED_NAME,0)

}