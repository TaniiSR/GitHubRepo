package com.task.githubrepo.di

import com.task.githubrepo.data.remote.RepoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesReposService(): RepoService = RepoService.createService()

}