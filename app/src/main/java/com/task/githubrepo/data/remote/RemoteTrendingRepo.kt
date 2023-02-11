package com.task.githubrepo.data.remote

import com.task.githubrepo.data.dtos.Repository
import com.task.githubrepo.data.remote.base.BaseRepo
import com.task.githubrepo.data.remote.base.NetworkResult
import javax.inject.Inject

class RemoteTrendingRepo @Inject constructor(private val service: RepoService) : BaseRepo(), IRemoteTrendingRepo {
    override suspend fun fetchRepo(): NetworkResult<Repository> {
      return safeApiCall { service.getTrendingRepos() }
    }
}