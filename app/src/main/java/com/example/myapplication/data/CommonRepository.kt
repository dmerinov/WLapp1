package com.example.myapplication.data

import com.example.myapplication.data.db.Local
import com.example.myapplication.data.network.Network
import com.example.myapplication.data.preferences.Preferences
import com.example.myapplication.mapper.toMonumentDomainModel
import com.example.myapplication.model.*

class CommonRepository(
    private val network: Network,
    private val prefs: Preferences,
    private val realm: Local
) : Repository {

    override fun getMonuments(success: (List<MonumentDomainModel>) -> Unit, error: () -> Unit) {

        network.getMonuments(
            success = {
                realm.setMonuments(it)
                success(it.map { it.toMonumentDomainModel() })
            },
            error = {
                val monuments = realm.getMonuments()
                if (monuments.isNotEmpty()) {
                    val deliveredMonument = processMonumentListLocal(monuments)
                    if (deliveredMonument.isNotEmpty())
                        success(deliveredMonument)
                    else
                        error()
                }
            }
        )
    }

    override fun getDetailMonument(
        id: String,
        success: (MonumentDomainDetailModel) -> Unit,
        error: () -> Unit
    ) {

        network.getDetailMonument(
            id = id,
            success = {
                realm.setMonumentDetail(it)
                success(processMonumentNetwork(it))
            },
            error = {
                if (realm.hasMonumentDetail(id)) {
                    val detailMonument = realm.getMonumentDetail(id)
                    success(processMonumentLocal(detailMonument))
                } else {
                    error()
                }

            }
        )
    }

    override fun addFavourite(id: String) {
        prefs.addFavouriteMonument(id)
    }

    override fun removeFavourite(id: String) {
        prefs.removeFavouriteMonument(id)
    }

    private fun processMonumentNetwork(
        monument: MonumentDetailDto
    ): MonumentDomainDetailModel {

        val favouritesIds: Set<String> = prefs.getFavourites()

        return MonumentDomainDetailModel(
            id = monument.id,
            address = monument.address,
            description = monument.description,
            email = monument.email,
            geocoordinates = monument.geocoordinates,
            phone = monument.phone,
            title = monument.title,
            transport = monument.transport,
            isFavourite = favouritesIds.contains(monument.id)
        )
    }

    private fun processMonumentLocal(
        monument: MonumentDetailVO
    ): MonumentDomainDetailModel {

        val favouritesIds: Set<String> = prefs.getFavourites()

        return MonumentDomainDetailModel(
            monument.id,
            monument.address,
            monument.description,
            monument.email,
            monument.geocoordinates,
            monument.phone,
            monument.title,
            monument.transport,
            favouritesIds.contains(monument.id)
        )
    }

    private fun processMonumentListLocal(monuments: List<MonumentVO>): List<MonumentDomainModel> {
        return monuments.map {
            it.toMonumentDomainModel()
        }
    }
}