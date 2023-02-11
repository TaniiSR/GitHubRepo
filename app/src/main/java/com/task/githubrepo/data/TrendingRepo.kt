package com.task.githubrepo.data

import com.task.githubrepo.data.ITrendingRepo
import com.task.githubrepo.data.local.ILocalSource
import com.task.githubrepo.data.dtos.Repository
import com.task.githubrepo.data.remote.IRemoteTrendingRepo
import com.task.githubrepo.data.remote.base.NetworkResult
import javax.inject.Inject

class TrendingRepo @Inject constructor(
    private val remoteSource: IRemoteTrendingRepo,
    private val localSource: ILocalSource
) : ITrendingRepo {
    override suspend fun fetchTrendingRepos(isFromCache: Boolean): NetworkResult<Repository> {
        return if (isFromCache) {
            val localData = localSource.getLocallySaveRepo()
            if (localData != null)
                NetworkResult.Success(data = localData)
            else
                getRemoteData()
        } else
            getRemoteData()
    }

    private suspend fun getRemoteData(): NetworkResult<Repository> {
        val response = remoteSource.fetchRepo()
        if (response is NetworkResult.Success)
            localSource.saveRepoLocally(response.data)
        return response
    }

}
