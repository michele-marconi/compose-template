package com.spindox.composetemplate.repository

import com.spindox.composetemplate.data.dao.BeerDao
import com.spindox.composetemplate.data.entity.Beer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepositoryImpl @Inject constructor(
    private val beerDao: BeerDao
) : DetailRepository {
    override fun loadBeerDetailFromDB(id: String): Flow<Beer> = beerDao.getBeerFromId(id)
}
