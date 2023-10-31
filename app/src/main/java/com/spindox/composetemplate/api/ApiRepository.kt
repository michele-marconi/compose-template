package com.spindox.composetemplate.api

import com.spindox.composetemplate.data.entity.Beer

class ApiRepository(private val apiService: PunkAPIService) : BaseRepo() {

    suspend fun getBeers(): Resource<List<Beer>> = safeApiCall {
        apiService.getBeers()
    }
}