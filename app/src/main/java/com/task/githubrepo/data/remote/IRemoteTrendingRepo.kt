package com.task.githubrepo.data.remote

import com.task.githubrepo.data.dtos.Repository
import com.task.githubrepo.data.remote.base.NetworkResult

interface IRemoteTrendingRepo {
    suspend fun fetchRepo(): NetworkResult<Repository>
}