package com.example.myapplication.data.db

import android.content.Context
import com.example.myapplication.mapper.toMonumentDetailVO
import com.example.myapplication.mapper.toMonumentVO
import com.example.myapplication.model.*
import com.google.gson.Gson
import io.realm.Realm
import io.realm.RealmConfiguration
import org.bson.types.ObjectId

class RealmDataSource(context: Context) : Local {

    companion object {
        private const val MONUMENTS_LIST = "REALM_MONUMENTS_LIST"
        private const val MONUMENT_DETAIL_ID = "REALM_MONUMENT_DETAIL"

        private fun getMonumentDetailId(id: String) = "${MONUMENT_DETAIL_ID}_$id"
    }

    private val realmName: String = "Database"
    private var config: RealmConfiguration
    private var backgroundThreadRealm: Realm
    private val gson = Gson()

    init {
        Realm.init(context)
        config = RealmConfiguration.Builder()
            .name(realmName)
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
        backgroundThreadRealm = Realm.getInstance(config)
        println(context.filesDir)
    }


    override fun hasMonuments(): Boolean {
        val realmList = backgroundThreadRealm.where(RealmMonumentDBModel::class.java)
            .equalTo("RealmJsonID", ObjectId.get()).findFirst()
        println("Fetched object by primary key: $")
        return realmList != null
    }

    override fun getMonuments(): List<MonumentVO> {

        var returnableObject = mutableListOf<MonumentVO>()
        val realmList = backgroundThreadRealm.where(RealmMonumentDBModel::class.java)
            .equalTo("RealmJsonID", ObjectId.get()).findFirst()
        println("Fetched object by primary key: $")

        if (realmList != null) {
            returnableObject.addAll(
                gson.fromJson(
                    realmList.jsonString,
                    Array<MonumentVO>::class.java
                ).toList()
            )
        } else {
            returnableObject.addAll(listOf(MonumentVO("-1", "-1", "empty object")))
        }
//        println(realmList)
        return returnableObject
    }

    override fun setMonuments(monuments: List<MonumentDto>) {
        var realmList = mutableListOf<MonumentVO>()
        val it = monuments.iterator()
        while (it.hasNext()) {
            realmList.add(it.next().toMonumentVO())
        }
//        realmList.map {
//            showMonument(it)
//        }
        val jsonString = gson.toJson(realmList)
        backgroundThreadRealm.executeTransaction { transactionRealm ->
            transactionRealm.copyToRealmOrUpdate(RealmMonumentDBModel(1, jsonString))
        }

    }

    override fun hasMonumentDetail(id: String): Boolean {
        val realmList = backgroundThreadRealm.where(RealmMonumentDBModel::class.java)
            .equalTo(getMonumentDetailId(id), ObjectId.get()).findFirst()
        println("Fetched object by primary key: $")
        return realmList != null
    }

    override fun getMonumentDetail(id: String): MonumentDetailVO {
        var returnableObject = MonumentDetailVO()
        val fetchedObject = backgroundThreadRealm.where(RealmMonumentDetailDBModel::class.java)
            .equalTo(getMonumentDetailId(id), ObjectId.get()).findFirst()
        println("Fetched object by primary key: $")

        if (fetchedObject != null) {
            returnableObject =
                gson.fromJson(
                    getMonumentDetailId(id),
                    MonumentDetailVO::class.java
                )

        }
        println(returnableObject)
        return returnableObject
    }

    override fun setMonumentDetail(monument: MonumentDetailDto) {
        var realmMonument = monument.toMonumentDetailVO()
        println(realmMonument)
        val jsonString = gson.toJson(realmMonument)
        backgroundThreadRealm.executeTransaction { transactionRealm ->
            transactionRealm.copyToRealmOrUpdate(
                RealmMonumentDBModel(
                    realmMonument.id.toInt(),
                    jsonString
                )
            )
        }

    }

    private fun showMonument(monument: MonumentVO) {
        println("id: ${monument.id}")
        println("title: ${monument.title}")
        println("coord: ${monument.geocoordinates}")
    }
}