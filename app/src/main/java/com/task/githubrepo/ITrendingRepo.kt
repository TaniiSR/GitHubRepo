package com.task.githubrepo

import com.task.githubrepo.data.dtos.Repository
import com.task.githubrepo.data.remote.base.NetworkResult

interface ITrendingRepo {
   suspend fun fetchTrendingRepos(): NetworkResult<Repository>
}