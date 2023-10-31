package com.spindox.composetemplate.api

import com.spindox.composetemplate.data.entity.Beer
import retrofit2.Response
import retrofit2.http.GET

interface PunkAPIService {

    // Just a simple GET request to get 80 beers
    @GET("beers?page=1&per_page=80")
    suspend fun getBeers(): Response<List<Beer>>
}
