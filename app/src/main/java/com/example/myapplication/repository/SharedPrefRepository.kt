package com.example.myapplication.repository

/* class SharedPrefRepository : CommonRepository {

    //val prefs = SharedPreferences.getSharedPreferences.("STRING",0)

    override fun getMonuments(success: (List<MonumentDto>) -> Unit, error: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getMonumentsInfo(
        monumentID: String,
        success: (MonumentDetailDto) -> Unit,
        error: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    fun loadMonuments(view: MonumentView) =
       if (prefs.contains("MONUMENT_LIST")) {
            //pass the local list
            //  view.showMonuments(retrieveMonuments(prefs))
        } else {
            //connect to API and save list locally
            getMonuments(
                success = {
                    //        saveMonuments(prefs,it)
                    //        view.showMonuments(retrieveMonuments(prefs))
                },
                error = {
                    println("error getting the response")
                }
            )
        }

    private fun retrieveMonuments(prefs: SharedPreferences): List<MonumentDto> {
        val stringPref = prefs.getString("MONUMENT_LIST", "")
        return Gson().fromJson<List<MonumentDto>>(stringPref, MonumentList::class.java)
    }

    private fun saveMonuments(prefs: SharedPreferences, it: List<MonumentDto>) {
        val editor = prefs.edit()
        val stringList = Gson().toJson(it)
        editor.putString("MONUMENT_LIST", stringList)
        editor.apply()
    }
}*/