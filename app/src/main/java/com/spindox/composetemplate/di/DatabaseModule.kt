package com.spindox.composetemplate.di

import android.content.Context
import androidx.room.Room
import com.spindox.composetemplate.R
import com.spindox.composetemplate.data.AppDatabase
import com.spindox.composetemplate.data.dao.BeerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context.applicationContext, AppDatabase::class.java, context.getString(
            R.string.app_name
        )
    ).build()

    @Singleton
    @Provides
    fun provideMessageDao(appDatabase: AppDatabase): BeerDao = appDatabase.beerDao
}
