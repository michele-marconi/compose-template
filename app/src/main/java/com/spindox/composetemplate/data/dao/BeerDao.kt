package com.spindox.composetemplate.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.spindox.composetemplate.data.entity.Beer
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {
    @Upsert
    suspend fun insertOrUpdateBeers(beersList: List<Beer>)

    @Query("SELECT * FROM Beers")
    fun getBeers(): Flow<List<Beer>>
}
