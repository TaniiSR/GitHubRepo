package com.task.githubrepo

import com.task.githubrepo.data.dtos.Repository
import com.task.githubrepo.data.remote.IRemoteTrendingRepo
import com.task.githubrepo.data.remote.base.NetworkResult

class TrendingRepo(private val remoteSource : IRemoteTrendingRepo) : ITrendingRepo {
    override suspend fun fetchTrendingRepos(): NetworkResult<Repository> {
        return remoteSource.fetchRepo()
    }

}
