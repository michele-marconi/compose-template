package com.spindox.composetemplate.repository

import com.spindox.composetemplate.api.ApiRepository
import com.spindox.composetemplate.api.Resource
import com.spindox.composetemplate.data.dao.BeerDao
import com.spindox.composetemplate.data.entity.Beer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val beerDao: BeerDao,
    private val webService: ApiRepository
) : HomeRepository {
    override fun loadBeersFromDB(): Flow<List<Beer>> = beerDao.getBeers()
    override suspend fun insertBeersToDB(beers: List<Beer>) = beerDao.insertOrUpdateBeers(beers)
    override suspend fun getBeersFromCloud(): Resource<List<Beer>> = webService.getBeers()
}
