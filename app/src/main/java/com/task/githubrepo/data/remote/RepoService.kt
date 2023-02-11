package com.task.githubrepo.data.remote

import com.task.githubrepo.data.dtos.Repository
import retrofit2.Response

interface RepoService {
    suspend fun getTrendingRepos(): Response<Repository>
}
