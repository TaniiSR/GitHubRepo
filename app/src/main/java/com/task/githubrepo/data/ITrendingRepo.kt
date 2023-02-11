package com.task.githubrepo.data

import com.task.githubrepo.data.dtos.Repository
import com.task.githubrepo.data.remote.base.NetworkResult

interface ITrendingRepo {
   suspend fun fetchTrendingRepos(isFromCache: Boolean): NetworkResult<Repository>
}
