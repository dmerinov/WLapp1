package com.example.myapplication.data.db

import android.content.Context
import com.example.myapplication.mapper.toMonumentDetailVO
import com.example.myapplication.mapper.toMonumentVO
import com.example.myapplication.model.MonumentDetailDto
import com.example.myapplication.model.MonumentDetailVO
import com.example.myapplication.model.MonumentDto
import com.example.myapplication.model.MonumentVO
import com.google.gson.Gson
import io.realm.Realm
import io.realm.RealmConfiguration

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
        val realmList = backgroundThreadRealm.where(MonumentVO::class.java)
            .findAll()
        println("Fetched object by primary key: $")
        return realmList != null
    }

    override fun getMonuments(): List<MonumentVO> {

        var returnableObject = mutableListOf<MonumentVO>()
        val realmList = backgroundThreadRealm.where(MonumentVO::class.java)
            .findAll()

        if (realmList != null) {
            returnableObject.addAll(backgroundThreadRealm.copyFromRealm(realmList))

        } else {
            returnableObject.addAll(listOf(MonumentVO("-1", "-1", "empty object")))
        }
//        println(realmList)
        return returnableObject
    }

    override fun setMonuments(monuments: List<MonumentDto>) {
        val it = monuments.iterator()
        while (it.hasNext()) {
            backgroundThreadRealm.executeTransaction { transactionRealm ->
                transactionRealm.copyToRealmOrUpdate(it.next().toMonumentVO())
            }
        }

    }

    override fun hasMonumentDetail(id: String): Boolean {
        val realmList = backgroundThreadRealm.where(MonumentDetailVO::class.java)
            .findAll()
        println("Fetched object by primary key: $")
        return realmList != null
    }

    override fun getMonumentDetail(id: String): MonumentDetailVO {
        var returnableObject = MonumentDetailVO()
        val fetchedObject = backgroundThreadRealm.where(MonumentDetailVO::class.java)
            .equalTo("id", id).findFirst()

        if (fetchedObject != null) {
            returnableObject = backgroundThreadRealm.copyFromRealm(fetchedObject)

        }
        println(returnableObject)
        return returnableObject
    }

    override fun setMonumentDetail(monument: MonumentDetailDto) {
        var realmMonument = monument.toMonumentDetailVO()
        showMonument(realmMonument)
        backgroundThreadRealm.executeTransaction { transactionRealm ->
            transactionRealm.copyToRealmOrUpdate(
                realmMonument
            )
        }

    }

    private fun showMonument(monument: MonumentDetailVO) {
        println("id: ${monument.id}")
        println("title: ${monument.title}")
        println("coord: ${monument.geocoordinates}")
    }
}