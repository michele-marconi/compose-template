package com.spindox.composetemplate.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.spindox.composetemplate.data.dao.BeerDao
import com.spindox.composetemplate.data.entity.Beer

@Database(
    version = 1,
    entities = [Beer::class],
    exportSchema = true
)
@TypeConverters(
    value = [RoomTypeConverters::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract val beerDao: BeerDao
}
