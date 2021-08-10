package com.example.myapplication.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MonumentVO(
    @PrimaryKey open var id: String = "id",
    open var geocoordinates: String = "geo",
    open var title: String = "title"
) : RealmObject()

//open class RealmMonumentDBModel(
//    @PrimaryKey open var jsonID: Int = -1,
//    open var jsonString: String = "jsonS"
//) : RealmObject()

open class MonumentDetailVO(
    @PrimaryKey open var id: String = "id",
    open var address: String = "addresss",
    open var description: String = " description",
    open var email: String = "email",
    open var geocoordinates: String = "geocoordinates",
    open var phone: String = "phone",
    open var title: String = "title",
    open var transport: String = "transport"
) : RealmObject()

//open class RealmMonumentDetailDBModel(
//    @PrimaryKey open var jsonID: Int = -1,
//    open var jsonDetailID: String = "jsonSDet"
//) : RealmObject()