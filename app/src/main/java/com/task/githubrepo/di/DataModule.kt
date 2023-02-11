package com.task.githubrepo.di

import com.task.githubrepo.data.ITrendingRepo
import com.task.githubrepo.data.TrendingRepo
import com.task.githubrepo.data.local.ILocalSource
import com.task.githubrepo.data.local.LocalData
import com.task.githubrepo.data.remote.IRemoteTrendingRepo
import com.task.githubrepo.data.remote.RemoteTrendingRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideRemoteSource(remoteSource: RemoteTrendingRepo): IRemoteTrendingRepo

    @Binds
    @Singleton
    abstract fun providesLocalSource(localData: LocalData): ILocalSource

    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: TrendingRepo): ITrendingRepo
}