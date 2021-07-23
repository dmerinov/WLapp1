package com.example.myapplication.repository

class SourceRepository(private val networkR: NetworkRepository) {

    /* fun getMonuments(): List<MonumentDto> {
         val list = mutableListOf<MonumentDto>()
         networkR.getMonuments(
             success = {
                list.addAll(it)
             },
             error = {
                 println("error getting the response")
             })
         return list
     }

     fun getMonumentInfo(monumentID: String) : MonumentDetailDto {
         lateinit var monument: MonumentDetailDto
         networkR.getMonumentsInfo(
             monumentID,
             success = {
             monumentit
         },
             error = {
                 println("error getting the response")
             }
         )
         val userService: ApiService =
             RetrofitResource.getRetrofit().create(ApiService::class.java)
         val result: Call<MonumentDetailDto> = userService.getPostById(monumentID)

         result.enqueue(object : Callback<MonumentDetailDto> {
             override fun onFailure(call: Call<MonumentDetailDto>, t: Throwable) {
                 error()
             }

             override fun onResponse(
                 call: Call<MonumentDetailDto>,
                 response: Response<MonumentDetailDto>
             ) {
                 if (response.isSuccessful) {
                     response.body()?.let { success(it) }
                 }
             }
         })
     }*/

}