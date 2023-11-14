package com.spindox.composetemplate.repository

import com.spindox.composetemplate.data.entity.Beer
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun loadBeerDetailFromDB(id: String): Flow<Beer>
}
