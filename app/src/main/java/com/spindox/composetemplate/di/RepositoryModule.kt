package com.spindox.composetemplate.di

import com.spindox.composetemplate.repository.DetailRepository
import com.spindox.composetemplate.repository.DetailRepositoryImpl
import com.spindox.composetemplate.repository.HomeRepository
import com.spindox.composetemplate.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}
