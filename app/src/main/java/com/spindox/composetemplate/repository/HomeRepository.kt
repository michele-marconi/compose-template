package com.spindox.composetemplate.repository

import com.spindox.composetemplate.api.Resource
import com.spindox.composetemplate.data.entity.Beer
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun loadBeersFromDB(): Flow<List<Beer>>
    suspend fun insertBeersToDB(beers: List<Beer>)
    suspend fun getBeersFromCloud(): Resource<List<Beer>>
}
