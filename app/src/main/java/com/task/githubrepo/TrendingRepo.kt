package com.task.githubrepo

import com.task.githubrepo.data.dtos.Item
import com.task.githubrepo.data.dtos.Repository

class TrendingRepo(private val service: RepoService) : BaseRepo(),  ITrendingRepo {
    override suspend fun fetchRepo(): NetworkResult<Repository> {
      return safeApiCall { service.getTrendingRepos() }
    }
}